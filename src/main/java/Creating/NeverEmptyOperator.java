package Creating;

import io.reactivex.Observable;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 14:13
 * Description:
 * Observable.never()方法将中断流，并不会发送任何通知
 * Observable.empty()方法将中断流，会发送complete通知
 */


class NeverEmptyOperator {

    static void testNever() {
        Observable<Integer> never = Observable.never();
        never.subscribe(new CommonObserver());

    }

    static void testEmpty() {
        Observable<Integer> never = Observable.empty();
        never.subscribe(new CommonObserver());

    }


    public static void main(String[] args) {
        testNever();
        testEmpty();
    }
}
