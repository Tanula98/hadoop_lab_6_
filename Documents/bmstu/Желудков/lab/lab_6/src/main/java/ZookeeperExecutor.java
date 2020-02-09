import akka.actor.ActorRef;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZookeeperExecutor implements Watcher {

    private ZooKeeper zoo;

    ZookeeperExecutor(int serverPort) throws IOException, KeeperException, InterruptedException {

        //создаем доступ к zookeeper
        zoo = new ZooKeeper(
                ZookeeperAppConstants.ZOOKEEPER_SERVER + ":" + ZookeeperAppConstants.ZOOKEEPER_PORT,
                ZookeeperAppConstants.ZOOKEEPER_SESSION_TIMEOUT,
                this
        );

        //собираем адрес текущего сервера
        String serverUrl = "http://" + ZookeeperAppConstants.HOST + ":" + serverPort;

        //регистрируемся в зоокипере
        zoo.create(
                ZookeeperAppConstants.SERVER_NODE,
                serverUrl.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL
        );
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

        try {
            //получаем спиок серверов
            List<String> serversNodes = zoo.getChildren(ZookeeperAppConstants.SERVERS_NODE, this);
            List<String> serversList = new ArrayList<>();

            //для каждого сервера получаем адрес
            for (String s : serversNodes){
                byte[] server = zoo.getData(ZookeeperAppConstants.SERVERS_NODES_PATH + s, false, null);
                serversList.add(new String(server));
            }

        } catch (KeeperException | InterruptedException e){
            e.printStackTrace();
        }

    }
}
