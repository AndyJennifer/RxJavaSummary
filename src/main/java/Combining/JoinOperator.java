package Combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 14:53
 * Description:
 */

public class JoinOperator {

    static void test() {
        Observable<Long> observable1 = Observable.intervalRange(1, 5,0,1,TimeUnit.SECONDS);
        Observable<Long> observable2 = Observable.intervalRange(6, 5,0,1,TimeUnit.SECONDS);
        observable1.join(observable2, new Function<Long, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(Long integer) throws Exception {
                return Observable.timer(1, TimeUnit.SECONDS);
            }
        }, new Function<Long, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(Long integer) throws Exception {
                return Observable.timer(0, TimeUnit.SECONDS);
            }
        }, new BiFunction<Long, Long, String>() {
            @Override
            public String apply(Long integer, Long integer2) throws Exception {
                return integer + "---result---" + integer2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        test();
        Thread.sleep(100000);//这里保证虚拟机不停止运行，
    }
}
