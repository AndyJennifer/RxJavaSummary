package rxjava.observables;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * Author:  andy.xwt
 * Date:    2019/11/18 22:59
 * Description: Maybe可以看做是Completable与Single的结合体，它拥有onComplete onError onSuccess
 * 同样只能发送一个事件。如果MaybeEmitter先调用了onComplete()，即使后面再调用了onSuccess()也不会发射任何数据。
 * 我们也可以将 Maybe 转换成Observable、Flowable、Single，只需相应地调用toObservable()、toFlowable()、toSingle()。
 */

class MaybeObservable {

    public static void main(String[] args) {
        Maybe.create(new MaybeOnSubscribe<Integer>() {
            @Override
            public void subscribe(MaybeEmitter<Integer> emitter) throws Exception {
                emitter.onComplete();
                emitter.onSuccess(1);
            }
        }).subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {
                System.out.println("onSuccess" + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
