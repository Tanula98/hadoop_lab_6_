import akka.actor.AbstractActor;

import java.util.List;

public class CacheActor extends AbstractActor {

    private List<String> serversList;

    static Props props() {
        return Props.create(CacheActor.class);
    }
    
    @Override
    public Receive createReceive() {

    }

}
