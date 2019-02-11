package Filtering;

import Creating.CommonObserver;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 13:00
 * Description:filter操作符会过滤掉不满足条件的事件
 */

public class FilterOperator {

    static void test() {
        Observable.range(1, 20).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                //true表示不过滤。false表示过滤掉
                return integer >= 5;
            }
        }).subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
        test();
    }
}
