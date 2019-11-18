package Combining;

import common.CommonObserver;
import io.reactivex.Observable;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 14:45
 * Description:startWith操作符会在被观察者发送的事件流开始的位置添加事件。
 */

public class StartWithOperator {

    static void test() {
        Observable.range(1, 5)
                .startWith(0)
                .subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
        test();
    }
}
