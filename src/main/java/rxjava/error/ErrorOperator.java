package rxjava.error;

import common.CommonObserver;
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
        }).subscribe(new CommonObserver());
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
                emitter.onError(new RuntimeException("错误了"));
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                return Observable.range(3, 3);
            }
        }).subscribe(new CommonObserver());
    }



    /**
     * onExceptionResumeNext与onErrorResumeNext操作符类似，但是需要注意的是前者指接受Exception，
     * 如果当前抛出Throwable错误，那么将不会产生新的被观察者，抛出的Throwable错误，任然会被观察者接受，且这个观察链将会结束
     */
    static void testOnExceptionResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
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
        }).subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
        testErrorReturn();
//        testErrorResumeNext();
//        testOnExceptionResumeNext();
    }
}
