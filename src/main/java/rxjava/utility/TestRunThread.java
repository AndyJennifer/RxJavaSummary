package rxjava.utility;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2019/11/18 13:33
 * Description:测试RxJava中观察者与被观察者相应方法运行的线程
 */


class TestRunThread {


    /**
     * 从输出结果中，可以看出
     * 观察者（Observer)的 onSubscribe 运行在当前线程
     * 观察者（Observer)的  onNext,onComplete，方法运行在 observeOn 指定的线程中
     * 被观察者（Observable)的  subscribe 方法运行在 subscribeOn 指定的线程中
     */
    public static void testNormalMethod() {
        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread run() 所在线程为 :" + ThreadUtil.getThreadName());
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        System.out.println("Observable subscribe() 所在线程为 :" + ThreadUtil.getThreadName());

                        emitter.onNext("1");
                        emitter.onNext("2");
                        emitter.onComplete();
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("Observer onSubscribe() 所在线程为 :" + ThreadUtil.getThreadName());
                            }

                            @Override
                            public void onNext(String s) {
                                System.out.println("Observer onNext() 所在线程为 :" + ThreadUtil.getThreadName());
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println("Observer onError() 所在线程为 :" + ThreadUtil.getThreadName());
                            }

                            @Override
                            public void onComplete() {
                                System.out.println("Observer onComplete() 所在线程为 :" + ThreadUtil.getThreadName());
                            }
                        });
            }
        }.start();
    }


    /**
     * 观察者中的doOnNext doOnError doOnComplete方法
     * 如果在 observeOn 方法前执行，那么就运行在 subscribeOn 方法所指定的线程，如果subscribeOn 方法没有指定，那么运行在所在线程中
     * 如果在 observeOn 方法之后执行，那么就运行在 observeOn 方法所指定的线程
     * 如果 observeOn 与 subscribeOn 都没有指定，那么运行在所在的线程中
     */
    public static void testDoOnMethod() {
        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread run() 所在线程为 :" + ThreadUtil.getThreadName());
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        System.out.println("Observable subscribe() 所在线程为 :" + ThreadUtil.getThreadName());
                        emitter.onNext("1");
                        emitter.onNext("2");
                        emitter.onError(new Throwable("错误"));
                        emitter.onComplete();
                    }
                })
                        .doOnNext(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                System.out.println("doOnNext() 所在线程为：" + ThreadUtil.getThreadName());
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                System.out.println("doOnComplete 所在线程为：" + ThreadUtil.getThreadName());
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                System.out.println("doOnError 所在线程为：" + ThreadUtil.getThreadName());
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("Observer onSubscribe() 所在线程为 :" + ThreadUtil.getThreadName());
                            }

                            @Override
                            public void onNext(String s) {
                                System.out.println("Observer onNext() 所在线程为 :" + ThreadUtil.getThreadName());
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println("Observer onError() 所在线程为 :" + ThreadUtil.getThreadName());
                            }

                            @Override
                            public void onComplete() {
                                System.out.println("Observer onComplete() 所在线程为 :" + ThreadUtil.getThreadName());
                            }
                        });
            }
        }.start();
    }


    public static void main(String[] args) {
//        testNormalMethod();
        testDoOnMethod();
        ThreadUtil.sleep();
    }
}
