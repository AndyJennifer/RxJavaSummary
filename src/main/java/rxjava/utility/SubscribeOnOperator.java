package rxjava.utility;

import common.CommonIntegerObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2019-03-10 23:41
 * Description:subscribeOn 规定了被观察者发送事件的运行线程
 * http://reactivex.io/documentation/operators/subscribeon.html
 */

public class SubscribeOnOperator {

    /**
     * 若Observable.subscribeOn（）多次指定被观察者 生产事件的线程，则只有第一次指定有效，其余的指定线程无效
     */
    public static void main(String[] args) {
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
                .subscribe(new CommonIntegerObserver());
        ThreadUtil.sleep();
    }
}
