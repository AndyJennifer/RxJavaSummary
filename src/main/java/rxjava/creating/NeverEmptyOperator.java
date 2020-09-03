package rxjava.creating;

import io.reactivex.Observable;
import common.CommonIntegerObserver;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 14:13
 * Description:
 * Observable.never()方法将中断流，并不会发送任何通知
 * Observable.empty()方法将中断流，会发送complete通知
 *
 * @see <a href=" https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Empty.html"/>
 */


class NeverEmptyOperator {

    static void testNever() {
        Observable<Integer> never = Observable.never();
        never.subscribe(new CommonIntegerObserver());

    }

    static void testEmpty() {
        Observable<Integer> never = Observable.empty();
        never.subscribe(new CommonIntegerObserver());

    }


    public static void main(String[] args) {
        testNever();
        testEmpty();
    }
}
