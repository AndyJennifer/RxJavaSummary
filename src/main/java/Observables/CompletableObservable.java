package Observables;

import Common.CommonObserver;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author:  andy.xwt
 * Date:    2019/11/18 22:48
 * Description:Completable 只有 onComplete 和 onError 事件，同时 Completable 并没有map、flatMap等操作符，它的操作符比起 Observable/Flowable 要少得多。
 * 与Single 类似只会调用这两个方法中的一个，而且只会调用一次，调用了任何一个方法之后，订阅关系终止。
 */


class CompletableObservable {


    private static void normalUse() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
//                emitter.onComplete();
                emitter.onError(new Throwable("error"));
            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("complete");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println(throwable.getMessage());
            }
        });
    }

    /**
     * 我们可以通过fromXXX操作符来创建一个Completable，Completable 经常会结合andThen操作符
     * 通过 andThen 操作或者 toXXX操作也可以将 Completable 转换成Observable、Flowable、Single以及Maybe
     */
    private static void useAndThen() {
        Completable
                .fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("run action");
                    }
                })
//                .toFlowable()
//                .toObservable()
                .andThen(Observable.range(5, 10))
                .subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
//        normalUse();
        useAndThen();
    }

}
