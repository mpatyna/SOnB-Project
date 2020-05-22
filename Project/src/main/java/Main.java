import server.ServerCluster;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //robię siatkę serwerów
        ServerCluster cluster = new ServerCluster();
        //uszkadzam połączenie
        cluster.deactivateConnection(0, 4);
        //odpalam plotkowanie z wiadmomością z parametry
        cluster.gossip("test");
        //wyświetlam stan wiadmomości na serwerach
        cluster.displayServerMessages();
        //naprawiam serwery i czyszczę wagi przy adresach
        cluster.cleanServerStatus();
    }
}
