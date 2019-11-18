package Observables;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Consumer;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2019/11/18 22:03
 * Description:
 * RxJava（以及它派生出来的RxGroovy和RxScala）中有一个名为Single的Observable变种。
 * <p>
 * Single类似于Observable，不同的是，它总是只发射一个值，或者一个错误通知，而不是发射一系列的值。
 * <p>
 * 因此，不同于Observable需要三个方法onNext, onError, onCompleted，订阅Single只需要两个方法：
 * <p>
 * onSuccess - Single发射单个的值到这个方法
 * onError - 如果无法发射需要的值，Single发射一个Throwable对象到这个方法
 * Single只会调用这两个方法中的一个，而且只会调用一次，调用了任何一个方法之后，订阅关系终止。
 * <p>
 * https://mcxiaoke.gitbooks.io/rxdocs/content/Single.html
 * http://reactivex.io/documentation/single.html
 */

class SingleObservable {

    public static void main(String[] args) {
        Single
                .create(new SingleOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                        //emitter.onSuccess(1); 只会调用这两个方法的一个，调用了任何一个方法之后，订阅关系终止
                        emitter.onError(new Throwable("error"));
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                });
        ThreadUtil.sleep();
    }
}
