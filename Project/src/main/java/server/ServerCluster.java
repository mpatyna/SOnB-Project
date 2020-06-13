package server;

import java.util.*;

public class ServerCluster {
    private List<Server> servers;
    private List<String> consolMessages= new ArrayList<>();
    private Map<Integer, Integer> chart = new HashMap<>();
    private int errorCounter;

    public ServerCluster() {
        this.servers = new ArrayList<>();
        //dodaję 11 serwerów
        for(int i = 0; i < 11; i++) {
            Server server = new Server();
            //każdy serwer ma adresy pozostałych, poza swoim
            for(int j = 0; j < 11; j++) {
                if(i != j) server.getAddresses().put(j, 1F);
            }
            servers.add(server);
        }
    }

    public List<String> gossip(String message) throws InterruptedException {
        errorCounter = 0;
        consolMessages= new ArrayList<>();
        //dodaję wiadomość do pierwszego serwera
        servers.get(0).getMessages().add(message);
        int counter = 0;
        consolMessages.add("Serwer nr. 0 otrzymał wiadomość: " + message + ". Rozpoczynam algorytm...");
        //co sekundę rozsyłam wiadomość do losowych serwerów
        while(!ifAllServersHaveMessage(message)) {
            consolMessages.add("----------------------------");
            consolMessages.add("Cykl nr. " + counter);
            List<Integer> destinations = new ArrayList<>();

            //znajduję serwery mające wiadomość do wysłania
            for (Server server:servers) {
                if(server.getMessages().contains(message)) {
                    destinations.add(servers.indexOf(server));
                }
            }
            //rozsyłam wiadomości
            for (Server server:servers) {
                if(destinations.contains(servers.indexOf(server))) {
                    send(server, message);
                }
            }
            counter++;
            if(errorCounter >= 20) {
                System.out.println("Osiągnięto limit błędów połączenia - część serwerów może być całkiem odłączona od sieci.");
                System.out.println("Algorytm zajął: " + counter + " cykli");
                break;
            }
        }
        if(errorCounter != 20) {
            consolMessages.add("Wszystkie serwery mają wiadomość. Zajęło to: " + counter + " cykli.");
        }
        else {
            System.out.println("Algorytm przerwany z powodu braku sprawnych połączeń. Ilość serwerów które mają wiadomość to: " + countServersWithMessage(message));
        }
        chart.put(new Integer(chart.size()),new Integer(counter));
        return consolMessages;
    }

    public boolean ifAllServersHaveMessage(String message) {
        boolean allServersHasMessage = false;
        int counter = countServersWithMessage(message);
        if(counter == servers.size()) allServersHasMessage = true;
        consolMessages.add("Serwery mające wiadomość: " + counter);
        return  allServersHasMessage;
    }

    private int countServersWithMessage(String message) {
        int counter = 0;
        for (Server server:servers) {
            if (server.getMessages().contains(message)) counter++;
        }
        return  counter;
    }

    public void send(Server from, String message) {
        Server destination;

        //jeżeli serwer nie ma działających połączeń, zwiększam errorCount i idę do kolejnego serwera
        int brokenConnections = 0;
        do {
            for (Map.Entry<Integer, Float> entry: from.getAddresses().entrySet()) {
                if(entry.getValue() <= 0) brokenConnections = brokenConnections + 1;
            }
            if(brokenConnections == 10) {
                destination = new Server();
                System.out.println("Nie można znaleźć działającego połączenia");
                break;
            }
            //losuję serwer, aż nie znajdę niezablokowanego
            int i = 0;
            do {
                Random rand = new Random();
                i = rand.nextInt(servers.size());
            }
            while(!from.getAddresses().containsKey(i));
            destination = servers.get(i);
        }
        while(from.getAddresses().get(servers.indexOf(destination)) == 0);

        if(destination.getAddresses().isEmpty()) {
            errorCounter = errorCounter + 1;
            System.out.println("Nie udało się znaleźć odpowiedniego adresata po raz: " + errorCounter);
        }
        else {
            consolMessages.add("Wysyłam wiadomość z: " + servers.indexOf(from) + ", do: " + servers.indexOf(destination));
            //obsługa zapsutego serwera
            if(from.getAddresses().get(servers.indexOf(destination)) < 0) {
                consolMessages.add("Połączenie uszkodzone, wysłanie nie powiodło się.");
                float tmp = from.getAddresses().get(servers.indexOf(destination));
                from.getAddresses().put(servers.indexOf(destination), tmp + 0.5F);
                if(from.getAddresses().get(servers.indexOf(destination)) == 0)
                    consolMessages.add("Dwukrotnie serwer nie odebrał - nie będę więcej próbować");
            }
            else if(destination.getMessages().contains(message)) {
                //serwer docelowy ma już wiadomość
                consolMessages.add("Serwer: " + servers.indexOf(destination) + " ma już tą wiadomość.");
                float tmp = from.getAddresses().get(servers.indexOf(destination));
                from.getAddresses().put(servers.indexOf(destination), tmp - 0.5F);
                if(from.getAddresses().get(servers.indexOf(destination)) == 0)
                    consolMessages.add("Dwukrotnie serwer nie odebrał - nie będę więcej próbować");
            }
            else {
                //wiadomość jest nowa
                destination.getMessages().add(message);
                consolMessages.add("Serwer: " + servers.indexOf(destination) + " odebrał wiadomość!");
            }
        }
    }

    public void deactivateConnection(int server1Id, int server2Id) {
        System.out.println("Zepsułem połączenie między serwerem " + server1Id + " i serverem " + server2Id);
        servers.get(server1Id).getAddresses().put(server2Id, -1F);
        servers.get(server2Id).getAddresses().put(server1Id, -1F);
    }
    public void activateConnection(int server1Id, int server2Id) {
        System.out.println("Naprawiłem połączenie między serwerem " + server1Id + " i serverem " + server2Id);
        servers.get(server1Id).getAddresses().put(server2Id, 1F);
        servers.get(server2Id).getAddresses().put(server1Id, 1F);
    }

    public List<String> displayServerMessages() {
        List<String> messages = new ArrayList<>();
        messages.add("Stan pamięci serwerów:");
        Integer no=1;
        for (Server server:servers) {
            messages.add("_____ Serwer "+no+"_____");
            messages.addAll(server.getMessages());
            no++;
        }
        return messages;
    }

    public void cleanServerStatus() {
        System.out.println("Naprawiem uszkodzone połączenia i przestawiam wagi w adresach");
        for (Server server:servers) {
            //czyszczę wagi dla adresów
            for (Map.Entry<Integer, Float> entry : server.getAddresses().entrySet()) {
                entry.setValue(1F);
            }
        }
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public Map<Integer, Integer> getChartData(){
        return this.chart;
    }
}
