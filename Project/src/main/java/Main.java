import server.Server;
import server.ServerCluster;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //robię siatkę serwerów
        ServerCluster cluster = new ServerCluster();
        //wyłączam serwer - można wyłączać dowolny poza 0
        //bo od 0 zaczynany jest algorytm
        cluster.deactivateServer(1);
        //odpalam plotkowanie z wiadmomością z parametry
        cluster.gossip("test");
        //wyświetlam stan wiadmomości na serwerach
        cluster.displayServerMessages();
        //naprawiam serwery i czyszczę wagi przy adresach
        cluster.cleanServerStatus();
    }
}
