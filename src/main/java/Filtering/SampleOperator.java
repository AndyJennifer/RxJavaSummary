package Filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 13:40
 * Description:sample操作符是定期扫描源Observable产生的结果，在指定的间隔周期内进行采样
 */

public class SampleOperator {

    //下面的例子中2秒一次采样
    static void test() {
        Observable.interval(1, TimeUnit.SECONDS)
                .sample(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });
    }

    public static void main(String[] args) throws InterruptedException {
        test();
        Thread.sleep(50000);
    }
}
