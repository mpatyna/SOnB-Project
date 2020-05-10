package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ServerCluster {
    private List<Server> servers;

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

    public void gossip(String message) throws InterruptedException {
        //dodaję wiadomość do pierwszego serwera
        servers.get(0).getMessages().add(message);
        int counter = 0;
        System.out.println("Serwer nr. 0 otrzymał wiadomość: " + message + ". Rozpoczynam algorytm...");
        //co sekundę rozsyłam wiadomość do losowych serwerów
        while(!ifAllServersHaveMessage(message)) {
            System.out.println("----------------------------");
            System.out.println("Cykl nr. " + counter);
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
            //symuluję cykliczne rosyłanie
            Thread.sleep(2000);
        }
        System.out.println("Wszystkie serwery mają wiadomość. Zajęło to: " + counter + " cykli.");
    }

    public boolean ifAllServersHaveMessage(String message) {
        boolean allServersHasMessage = false;
        int counter = 0;
        for (Server server:servers) {
            if (server.getMessages().contains(message)) counter++;
            //zepsute serwery traktuję jakby miały wiadomość, żeby pętla nie trwała w nieskończoność
            else if(!server.isActive()) counter++;
        }
        if(counter == servers.size()) allServersHasMessage = true;
        System.out.println("Serwery mające wiadomość lub popsute: " + counter);
        return  allServersHasMessage;
    }

    public void send(Server from, String message) {
        Server destination;
        //losuję serwer, aż nie znajdę niezablokowanego
        do {
            int i = 0;
            do {
                Random rand = new Random();
                i = rand.nextInt(servers.size());
            }
            while(!from.getAddresses().containsKey(i));
            destination = servers.get(i);
        }
        while(from.getAddresses().get(servers.indexOf(destination)) == 0);

        System.out.println("Wysyłam wiadomość z: " + servers.indexOf(from) + ", do: " + servers.indexOf(destination));
        //obsługa zapsutego serwera
        if(!destination.isActive()) {
            System.out.println("Serwer docelowy nie działa, wysłanie nie powiodło się");
            float tmp = from.getAddresses().get(servers.indexOf(destination));
            from.getAddresses().put(servers.indexOf(destination), tmp - 0.5F);
            if(from.getAddresses().get(servers.indexOf(destination)) == 0)
                System.out.println("Dwukrotnie serwer nie odebrał - nie będę więcej próbować");
        }
        if(destination.getMessages().contains(message)) {
            //serwer docelowy ma już wiadomość
            System.out.println("Serwer: " + servers.indexOf(destination) + " ma już tą wiadomość.");
            float tmp = from.getAddresses().get(servers.indexOf(destination));
            from.getAddresses().put(servers.indexOf(destination), tmp - 0.5F);
            if(from.getAddresses().get(servers.indexOf(destination)) == 0)
                System.out.println("Dwukrotnie serwer nie odebrał - nie będę więcej próbować");
        }
        else {
            //wiadomość jest nowa
            destination.getMessages().add(message);
            System.out.println("Serwer: " + servers.indexOf(destination) + " odebrał wiadomość!");
        }
    }

    public void deactivateServer(int id) {
        System.out.println("Zepsułem serwer numer: " + id);
        servers.get(id).setActive(false);
    }

    public void displayServerMessages() {
        System.out.println("Stan pamięci serwerów:");
        for (Server server:servers) {
            if(server.isActive())
                System.out.println(server.getMessages());
            else
                System.out.println("Serwer popsuty");
        }
    }

    public void cleanServerStatus() {
        System.out.println("Naprawiem uszkodzone serwry i przestawiam wagi w adresach");
        for (Server server:servers) {
            //uruchamiam wszystkie serwery
            server.setActive(true);
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
}
