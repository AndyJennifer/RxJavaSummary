package rxjava.creating;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import common.CommonObserver;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 14:22
 * Description:用from系列方法来管理一组数据流
 */


class FromOperator {

    static final Integer[] items = {0, 1, 2, 3, 4, 5};

    /**
     * 从数组中获取事件
     */
    static void testFromArray() {
        Observable<Integer> fromArray = Observable.fromArray(items);
        fromArray.subscribe(new CommonObserver());
    }

    /**
     * 从集合中获取事件
     */
    static void testFromIterable() {
        List<Integer> integers = Arrays.asList(items);
        Observable<Integer> fromIterable = Observable.fromIterable(integers);
        fromIterable.subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
//        testFromArray();
        testFromIterable();
    }
}
