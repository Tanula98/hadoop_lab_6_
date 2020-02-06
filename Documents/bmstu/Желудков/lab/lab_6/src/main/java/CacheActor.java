import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.util.List;

public class CacheActor extends AbstractActor {

    private List<String> serversList;

    static Props props() {
        return Props.create(CacheActor.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Servers.class, req->{
                    System.out.println(ZookeeperAppConstants.WATCHER_MESSAGE);
                    serversList = req.getServersList();
                })
                .match()
                .build();
    }

}
