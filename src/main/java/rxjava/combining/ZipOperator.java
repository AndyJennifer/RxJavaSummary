package rxjava.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 14:14
 * Description:Zip操作符返回一个Observable，它使用这个函数按顺序结合两个或多个Observables发射的数据项，
 * 然后它发射这个函数返回的结果。它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据。
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
                        return aLong + "-->" + aLong2 + "-->" + aLong3;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    public static void main(String[] args) {
        test();
        ThreadUtil.sleep();//这里保证虚拟机不停止运行
    }
}
