package rxjava.error;

import common.CommonObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Author:  andy.xwt
 * Date:    2019/2/11 10:54
 * Description:
 * retry()：出现错误时，让被观察者重新发送数据，如果一直错误将一直重新发送
 * retry(long times)：出现错误时，则让被观察者重新发送相应次数的事件,times重试次数,需要注意的是当重试相应次数后，任然报错，那么相应观察者需要处理错误
 * retry(Predicate<? super Throwable> predicate)：出现错误时，判断是否需要重新发送数据,
 * retry(BiPredicate<? super Integer, ? super Throwable> predicate)：第一个参数为重试的次数,基本与上一个方法一样。
 * retry(long times, Predicate<? super Throwable> predicate),带参数的重试
 */


class RetryOperator {

    /**
     * retry()出现错误时，让被观察者重新发送数据，如果一直错误将一直重新发送
     */
    static void testRetry() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("错误了"));
            }
        }).retry().subscribe(new CommonObserver());
    }

    /**
     * retry(long times),出现错误时，则让被观察者重新发送相应次数的事件,times重试次数
     */
    static void testRetryTimes() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("错误了"));
            }
        }).retry(2).subscribe(new CommonObserver());
    }


    /**
     * retry(Predicate<? super Throwable> predicate),出现错误时，判断是否需要重新发送数据,
     */
    static void testRetryPredicate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("错误了"));
            }
        }).retry(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return throwable.getMessage().equals("错误了");

            }
        }).subscribe(new CommonObserver());
    }

    /**
     * retry(BiPredicate<? super Integer, ? super Throwable> predicate),第一个参数为重试的次数
     */
    static void testRetryBiPredicate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("错误了"));
            }
        }).retry(new BiPredicate<Integer, Throwable>() {
            @Override
            public boolean test(Integer integer, Throwable throwable) throws Exception {
                System.out.println("重试次数" + integer);
                return throwable.getMessage().equals("错误了");
            }
        }).subscribe(new CommonObserver());
    }

    /**
     * retry(long times, Predicate<? super Throwable> predicate),带参数的重试
     */
    static void testRetryTimesAndPredicate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("错误了"));
            }
        }).retry(3, new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return throwable.getMessage().equals("错误了");
            }
        }).subscribe(new CommonObserver());
    }

    /**
     * retryWhen()遇到错误时，将发生的错误传递给一个新的被观察者，并决定是否需要重新订阅元素被观察者发送事件
     */
    static void testRetryWhen() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("错误了"));
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {

                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
//                        // 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
//                        // 该异常错误信息可在观察者中的onError（）中获得
                        return Observable.error(new Throwable("retryWhen结束"));
//
//                        //若返回的Observable发送的事件 = Next事件，则原始的Observable重新发送事件（若持续遇到错误，则持续重试
////                        return Observable.just(3);
                    }
                });
            }
        }).subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
//        testRetry();
//        testRetryTimes();
//        testRetryPredicate();
//        testRetryBiPredicate();
//        testRetryTimesAndPredicate();
        testRetryWhen();
    }
}
