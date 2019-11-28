package rxjava.transforming;

import common.CommonObserver;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Author:  andy.xwt
 * Date:    2019-02-04 23:45
 * Description:map操作符将当前被观察者发送的事件转换为另一种事件
 */

public class MapOperator {

    static void test() {
        Observable.just("1").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return Integer.parseInt(s);
            }
        }).subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
        test();
    }
}
