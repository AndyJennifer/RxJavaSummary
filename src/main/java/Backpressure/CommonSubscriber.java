package Backpressure;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Author:  andy.xwt
 * Date:    2019-03-11 16:47
 * Description:
 */

public class CommonSubscriber implements Subscriber<Integer> {

    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("onSubscribe");
        //request方法决定观察者能够接收多少个事件，多出的事件放在缓存区中,
        //该方法可以在外部调用，也就是被观察者发送事件，会先放入缓冲区中，可以通过调用该方法重缓存区中取事件
        s.request(Integer.MAX_VALUE);
    }


    @Override
    public void onNext(Integer integer) {
        System.out.println("Next: " + integer);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        System.out.println("onError: " + t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
