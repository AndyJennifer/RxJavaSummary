package Utility;

import Creating.CommonObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:  andy.xwt
 * Date:    2019-03-10 23:41
 * Description:subscribeOn规定了被观察者发送事件的运行线程
 */

public class SubscribeOnOperator {

    public static void main(String[] args) throws InterruptedException {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                for (int i = 1; i < 5; i++) {
                    emitter.onNext(i);
                }
                System.out.println("线程名称：" + Thread.currentThread().getName());
                emitter.onComplete();

            }

        })
                .subscribeOn(Schedulers.newThread())//只有第一次有效，因为包装类的调用关系
                .subscribeOn(Schedulers.io())
                .subscribe(new CommonObserver());
        Thread.sleep(1000);
    }
}
