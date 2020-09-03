package rxjava.error;

import common.CommonIntegerObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;

/**
 * Author:  andy.xwt
 * Date:    2019/2/11 10:06
 * Description:
 * @see <a href="https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Catch.html"/>
 */


class ErrorOperator {

    /**
     * 当遇到错误时，发送一个特殊事件并终止，本质是当前被观察者发送一个错误事件的时候，
     * 中间观察者ObservableOnErrorReturn重新调用被观察者的onNext方法将onErrorReturn中的返回值发送
     * 并同时调用onComplete方法
     */
    static void testErrorReturn() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("错误了"));
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                //当发生错误
                return 123;
            }
        }).subscribe(new CommonIntegerObserver());
    }

    /**
     * onErrorResumeNext方法当遇到错误时，将会重新创建一个新的被观察者
     */
    static void testErrorResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("错误了"));
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                return Observable.range(3, 3);
            }
        }).subscribe(new CommonIntegerObserver());
    }


    /**
     * 和onErrorResumeNext类似，onExceptionResumeNext方法返回一个镜像原有Observable行为的新Observable，
     * 也使用一个备用的Observable，不同的是，
     * 如果onError收到的Throwable不是一个Exception，它会将错误传递给观察者的onError方法，不会使用备用的Observable。
     */
    static void testOnExceptionResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
//                emitter.onError(new Error("错误了"));
                emitter.onError(new Exception("错误了"));
            }
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(3);
                observer.onNext(4);
                observer.onNext(5);
                observer.onComplete();
            }
        }).subscribe(new CommonIntegerObserver());
    }

    public static void main(String[] args) {
//        testErrorReturn();
//        testErrorResumeNext();
        testOnExceptionResumeNext();
    }
}
