package rxjava.transforming;

import common.CommonObserver;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Author:  andy.xwt
 * Date:    2019-02-05 23:17
 * Description:scan操作符把当前的发送事件与下一发送事件集合产生新的事件并发送
 */

public class ScanOperator {

    static void test() {
        // 1->发送1
        //1+2->发送3
        //3+3->发送6
        Observable.range(1, 3).scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
        test();
    }
}
