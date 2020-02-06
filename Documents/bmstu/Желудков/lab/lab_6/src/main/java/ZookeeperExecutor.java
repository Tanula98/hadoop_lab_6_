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
                byte[] server = zoo.getData(ZookeeperAppConstants.SERVERS_NODES_PATH + s, false, null);
                serversList.add(new String(server));
            }

        } catch (KeeperException | InterruptedException e){
            e.printStackTrace();
        }

    }
}
