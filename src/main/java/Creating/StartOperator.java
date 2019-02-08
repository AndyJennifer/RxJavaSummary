package Creating;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * Author:  andy.xwt
 * Date:    2019-02-03 21:33
 * Description: 创建一个发出类似函数指令的返回值的Observable
 *
 */

public class StartOperator {

    static void test() {
        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        }).subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
        test();
    }
}
