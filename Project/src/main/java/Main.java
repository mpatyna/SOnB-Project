import server.Server;
import server.ServerCluster;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ServerCluster cluster = new ServerCluster();
        cluster.gossip("test");
    }
}
