import akka.actor.ActorRef;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperExecutor implements Watcher {

    private ZooKeeper zoo;
    private ActorRef cacheActor;

    @Override
    public void process(WatchedEvent watchedEvent) {

        try {

            List<String> serversNodes = zoo.getChildren(ZookeeperAppConstants.SERVERS_NODE, this);
        }

    }
}
