package rxjava.creating;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 14:37
 * Description:创建一个可观测的，它发出一个整数序列，按给定的时间间隔隔开
 *
 * @see <a href="https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Interval.html"/>
 */

class IntervalOperator {

    private static void testInterval() {
        //第一个参数：第一个事件发送的延迟时间。
        //第二个参数：后续时间发送事件间隔。
        //第三个参数：时间单位
        Observable<Long> interval = Observable.interval(1, 1, TimeUnit.SECONDS);

        interval.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe: --->");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("onNext--->" + aLong);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Sequence complete.");
            }
        });

    }

    private static void testIntervalRange() {
        //第一个参数：开始数
        //第二个参数：事件个数
        //第三个参数：第一个事件延迟时间
        //第四个参数：后续事件发送时间间隔
        //第五个参数：时间间隔
        Observable<Long> longObservable = Observable.intervalRange(10, 20, 0, 1, TimeUnit.SECONDS);
        longObservable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("onNext--->" + aLong);
            }
        });
    }

    public static void main(String[] args) {
//        testInterval();
        testIntervalRange();

        /**
         * When you use the default scheduler (Schedulers.computation()) the observable emits on another thread.
         * If your program exits just after the subscribe then the observable is not given a chance to run.
         * Put in a long sleep just after the subscribe() call and you will see it working.
         *
         */
        ThreadUtil.sleep();//这里保证虚拟机不停止运行，
    }
}
