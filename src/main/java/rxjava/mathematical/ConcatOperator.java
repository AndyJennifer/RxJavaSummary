package rxjava.mathematical;

import java.util.ArrayList;
import java.util.List;

import common.CommonIntegerObserver;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author:  andy.xwt
 * Date:    2019-02-06 00:33
 * Description:concat操作符 将多个被观察者发送的事件组合起来
 * 与{@link rxjava.combining.MergeOperator}的区别，是按照被观察者的顺序一个一个发送事件的。不会出现抢占的情况
 */

public class ConcatOperator {

    //concat有个数限制
    static void testConcat() {
        Observable.concat(Observable.range(1, 5), Observable.range(5, 5))
                .subscribe(new CommonIntegerObserver());
    }

    //concatArray没有个数限制
    static void testConcatArray() {
        List<Observable<Integer>> list = new ArrayList<>();
        list.add(Observable.range(1, 5));
        list.add(Observable.range(5, 5));
        Observable.concat(list)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("---->"+integer);
                    }
                });

    }
    //delayError的作用是当整个事件流出现异常时，不会中断事件的传递，而是等到正常事件发送完毕后，才发送错误事件
    static void testConcatDelayError() {
        Observable.concatArrayDelayError(Observable.range(1, 5), Observable.error(new RuntimeException("rxjava/error")), Observable.range(5, 5))
                .subscribe(new CommonIntegerObserver());

    }

    public static void main(String[] args) {
        testConcat();
//        testConcatArray();
//        testConcatDelayError();
    }
}
