package rxjava.filtering;

import java.util.concurrent.TimeUnit;

import common.CommonIntegerObserver;
import io.reactivex.Observable;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 13:12
 * Description:take操作符会获取字符串中的前几个，或者后几个事件。
 */

public class TakeOperator {

    static void testDefaultTake() {
        Observable.range(1, 100).take(2).subscribe(new CommonIntegerObserver());
    }

    //如果采用take获取某个时间段的事件，
    static void testTakeWithTime() {
        Observable.range(1, 1000).take(20, TimeUnit.MILLISECONDS).subscribe(new CommonIntegerObserver());
    }

    static void testTakeLast() {
        Observable.range(1, 100).takeLast(2).subscribe(new CommonIntegerObserver());
    }

    public static void main(String[] args) {
//        testDefaultTake();
//        testTakeLast();
        testTakeWithTime();
    }
}
