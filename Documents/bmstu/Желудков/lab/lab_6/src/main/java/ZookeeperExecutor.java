import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class ZookeeperExecutor implements Watcher {

    private ZooKeeper zoo;
    private ActorRef cacheActor;

    @Override
    public void process(WatchedEvent watchedEvent) {

        try {

            List<String> serversNodes = zoo.getChildren(ZookeeperAppConstants.SERVERS_NODE, this);
            List<String> serversList = new ArrayList<>();

            for (String s : serversNodes){

            }


        } catch (KeeperException | InterruptedException e){
            e.printStackTrace();
        }

    }
}
