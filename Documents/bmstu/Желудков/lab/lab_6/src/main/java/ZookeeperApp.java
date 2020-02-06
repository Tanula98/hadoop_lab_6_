import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class ZookeeperApp {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        int serverPort;
        serverPort = Integer.parseInt(args[0]);


        ActorSystem system = ActorSystem.create(ZookeeperAppConstants.ACTOR_SYSTEM_NAME);
        final Http http = Http.get(system);
    }
}
