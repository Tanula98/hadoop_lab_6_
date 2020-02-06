import akka.actor.ActorSystem;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class ZookeeperApp {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        int serverPort;
        serverPort = Integer.parseInt(args[0]);


        ActorSystem system = ActorSystem.create(ZookeeperAppConstants.ACTOR_SYSTEM_NAME);
        
    }
}
