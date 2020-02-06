import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class ZookeeperApp {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        int serverPort;
        serverPort = Integer.parseInt(args[ZookeeperAppConstants.SERVER_PORT_IDX_IN_ARGS]);
    }
}
