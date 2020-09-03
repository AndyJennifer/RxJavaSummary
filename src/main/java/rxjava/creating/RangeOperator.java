package rxjava.creating;

import io.reactivex.Observable;
import common.CommonIntegerObserver;

/**
 * Author:  andy.xwt
 * Date:    2019/2/1 10:31
 * Description:
 * RxJava将这个操作符实现为range函数，它接受两个参数，一个是范围的起始值，
 * 一个是范围的数据的数目。如果你将第二个参数设为0，将导致Observable不发射任何数据（如果设置为负数，会抛异常）。
 *
 * @see <a href="https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Range.html"/>
 */


class RangeOperator {

    static void test() {
        Observable<Integer> range = Observable.range(1, 10);
        range.subscribe(new CommonIntegerObserver());
    }

    public static void main(String[] args) {
        test();
    }
}
