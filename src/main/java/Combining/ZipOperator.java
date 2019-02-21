package Combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 14:14
 * Description:zip 操作符，将会合并两个观察者中的事件，合并的数量为两个被观察者发送最少的事件数目
 */

public class ZipOperator {

    static void test() {
        Observable.zip(
                Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
                , Observable.intervalRange(5, 6, 0, 2, TimeUnit.SECONDS)
                , Observable.intervalRange(10, 2, 0, 3, TimeUnit.SECONDS)
                , new Function3<Long, Long, Long, String>() {
                    @Override
                    public String apply(Long aLong, Long aLong2, Long aLong3) throws Exception {
                        return aLong + "-->" + aLong2+"-->"+aLong3;
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
