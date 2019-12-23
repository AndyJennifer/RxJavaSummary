package rxjava.combining;

import common.CommonObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 14:45
 * Description:startWith操作符会在被观察者发送的事件流开始的位置添加事件。
 */

public class StartWithOperator {

    /**
     * 输出0，1，2，3，4，5
     */
    static void test() {
        Observable.range(1, 5)
                .startWith(0)
                .subscribe(new CommonObserver());
    }

    /**
     * 当有多个startWith时按照顺序排列，最后的在最前
     * 输出-1，0，1，2，3，4，5
     */
    static void test2() {
        Observable.range(1, 5)
                .startWith(0)
                .startWith(-1)
                .subscribe(new CommonObserver());
    }

    /**
     * 到抛出异常时，会终止输出。
     */
    static void test3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onError(new Throwable("error"));
                emitter.onNext(1);
                emitter.onNext(2);
            }
        }).startWith(0).startWith(-1).subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
//        test();
//        test2();
        test3();
    }
}
