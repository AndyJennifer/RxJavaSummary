package Backpressure;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:  andy.xwt
 * Date:    2019-03-11 15:30
 * Description:
 * 在RxJava中事件订阅分为同步订阅与异步订阅：
 * <p>
 * 同步订阅：被观察者发送一个事件，必须等到观察者接受后，才能继续发送下一个事件
 * 异步订阅：被观察不需要等到观察者接受/处理事件后，才继续发送下一个事件，而是不断的发送事件，直到事件发送完毕
 * （在异步订阅中，发送的事件会先发送到缓冲区，等观察者从缓存区中取出事件来处理）
 * <p>
 * 出现的问题：被观察者发送事件太快，而观察者来不及接收所有事件，从而导致观察者无法及时处理所有发送过来的事件。最终
 * 导致缓冲区溢出，或者事件丢失。
 * <p>
 * 解决方案：背压，控制被观察者的发送事件的速度，或控制观察者接收事件的速度。添加缓存区中（缓冲区大小128）
 */

public class BackPressureOperator {

    /**
     * 异步订阅情况
     */
    public static void flowableAsnycNormal() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                System.out.println("发送事件1");
                emitter.onNext(1);
                System.out.println("发送事件2");
                emitter.onNext(2);
                System.out.println("发送事件4");
                emitter.onNext(3);
                System.out.println("发送事件4");
                emitter.onNext(4);
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR).observeOn(Schedulers.io()).subscribe(new CommonSubscriber());
    }

    /**
     * 同步订阅,同步订阅中当观察者接收/处理一个事件后，被观察者才会发送另一个事件。
     * 注意的是，同步订阅中Flowable是没有缓冲区这个概念的。
     */
    public static void flowableSnycNormal() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                System.out.println("发送事件1");
                emitter.onNext(1);
                System.out.println("发送事件2");
                emitter.onNext(2);
                System.out.println("发送事件3");
                emitter.onNext(3);
                System.out.println("发送事件4");
                emitter.onNext(4);
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR).subscribe(new CommonSubscriber());
    }

    public static void flowableSyncNotice() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                //在同步订阅中，可以获取被观察者可以处理的事件个数
                //注意：requested不能在异步订阅中获取，因为在不同的线程
                int requested = (int) emitter.requested();
                for (int i = 0; i < requested; i++) {
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(10);
                //在同步订阅中，request方法可以叠加。
                s.request(10);

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
        });
    }

    /**
     * 异步的情况下，当缓冲区满的情况，默认是128，
     */
    public static void bufferOut() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 130; i++) {
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                //这里被观察者发送了130个事件，但是观察者只消费了1个事件，会超过缓存区的大小，这里会报错
                s.request(1);
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
        });
    }

    /**
     * 背压的策略模式：
     * BackpressureStrategy.ERROR：当缓冲区大小满时，被观察者任然继续发送下一个事件，直接抛出MissingBackpressureException
     * BackpressureStrategy.MISSING：当缓冲区大小满时，友好提示
     * BackpressureStrategy.BUFFER：当缓冲区大小满时，将缓冲区设置为无效大，注意内存泄露的情况
     * BackpressureStrategy.DROP：当缓冲区大小满时，直接丢弃超过缓冲区大小的事件。
     * BackpressureStrategy.LATEST：当缓冲区大小满时，取最后发送的事件，也就是如果缓冲区大小为128，发送了150个事件，
     * 那么实际发送的事件为129 128 +（最后一个事件（事件序列号为150）
     */
    public static void backPressureStartegy() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 130; i++) {
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                //这里被观察者发送了130个事件，但是观察者只消费了1个事件，会超过缓存区的大小，这里会报错
                s.request(1);
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
        });
    }

    public static void main(String[] args) throws InterruptedException {
//        flowableAsnycNormal();
//        flowableSnycNormal();
//        flowableSyncNotice();
//        bufferOut();
        Thread.sleep(1000);
    }
}
