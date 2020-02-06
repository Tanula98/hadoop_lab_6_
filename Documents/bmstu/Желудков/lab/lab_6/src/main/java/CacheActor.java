import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.util.List;
import java.util.Random;

//создаем актор хранилище конфигурации.
public class CacheActor extends AbstractActor {

    private List<String> serversList;

    static Props props() {
        return Props.create(CacheActor.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                //Он принимает две команды —
                //список серверов (который отправит zookeeper watcher)
                .match(Servers.class, req->{
                    System.out.println(ZookeeperAppConstants.WATCHER_MESSAGE);
                    serversList = req.getServersList();
                })
                //запрос на получение случайного сервера
                .match(CacheActor.GetRandomServer.class, msg -> {
                    int randServerIdx = new Random().nextInt(serversList.size());
                })
                .build();
    }

    static class GetRandomServer {
    }

}
