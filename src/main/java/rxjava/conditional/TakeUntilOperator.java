package rxjava.conditional;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2019/11/14 23:07
 * Description:
 * http://reactivex.io/documentation/operators/takeuntil.html
 * 一直从事件流中取数据直到某样条件满足就不再获取数据
 */

class TakeUntilOperator {


    public static void main(String[] args)  {
//        takeUntilOne();
        takeUntilTwo();
    }

    /**
     * 下面的例子中，满足条件后就不会继续发送事件了。
     */
    private static void takeUntilOne() {
        Observable.just(1, 2, 3, 4, 5).takeUntil(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer == 4;//符合条件后就不在发送
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("integer=======" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("complete");
            }
        });
    }

    /**
     * 在下面的例子中，observable1一直会发送事件，直到observable2发送事件时，observable1会停止发送事件
     */
    private static void takeUntilTwo()  {
        Observable<Long> observable1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<String> observable2 = Observable.just("4").delay(3, TimeUnit.SECONDS);
        observable1.takeUntil(observable2)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("aLong==============" + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("complete");
                    }
                });
        ThreadUtil.sleep();

    }
}
