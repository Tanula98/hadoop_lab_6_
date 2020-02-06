//б. создаем с помощью api route в акка http сервер который принимает два
//параметра, и если счетчик не равен 0, то сначала получает новый урл сервера
//(от актора хранилища конфигурации) и делает запрос к нему с аналогичными
//query параметрами (url, counter) но счетчиком на 1 меньше. Либо осуществляет
//запрос по url из параметра

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;

import java.util.concurrent.CompletionStage;
import java.util.regex.Pattern;

public class HttpRouter extends AllDirectives {

    private final ActorRef cacheActor;

    HttpRouter(ActorSystem system) {
        cacheActor = system.actorOf(CacheActor.props(), ZookeeperAppConstants.CACHE_ACTOR_NAME);
    }


    public ActorRef getCacheActor() {
        return cacheActor;
    }

    Route createRoute(Http http) {
        return route(
                get(() -> parameter(ZookeeperAppConstants.URL_PARAMETER_NAME, (url) ->
                        parameter(ZookeeperAppConstants.COUNT_PARAMETER_NAME, (count) ->
                        {
                            int redirectCount = Integer.parseInt(count);
                            if (redirectCount != 0) {
                                return completeWithFuture(redirect(http, url, redirectCount));
                            } else {
                                return completeWithFuture(fetch(http, url));
                            }
                        }))

                )
        );

    }

////осуществляет запрос по url из параметра
    private CompletionStage<HttpResponse> fetch(Http http, String url) {
        return http.singleRequest(HttpRequest.create(url));
    }

    //сначала получает новый урл сервера
    ////(от актора хранилища конфигурации) и делает запрос к нему с аналогичными
    ////query параметрами (url, counter) но счетчиком на 1 меньше.
    private CompletionStage<HttpResponse> redirect(Http http, String url, int count) {
        return Patterns.ask()
    }
}
