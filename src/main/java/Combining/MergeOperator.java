package Combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 14:07
 * Description: 组合多个被观察者，将多个被观察者发送的事件，按照时间线进行组合，并发送
 */

public class MergeOperator {

    static void testMerge() {
        Observable.merge(
                Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS),
                Observable.intervalRange(5, 5, 1, 2, TimeUnit.SECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });
    }

    public static void main(String[] args) throws InterruptedException {
        testMerge();
        Thread.sleep(100000);//这里保证虚拟机不停止运行，
    }
}
