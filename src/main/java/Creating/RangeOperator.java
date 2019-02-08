package Creating;

import io.reactivex.Observable;

/**
 * Author:  andy.xwt
 * Date:    2019/2/1 10:31
 * Description:
 * 根据用户选择范围的起始点及其长度,range 运算符按顺序发出该范围类的连续整数
 * 如果你设置开始位置为0，那么不会发送任何时间，如果设置为负数将会抛出错误
 */


class RangeOperator {

    static void test() {
        Observable<Integer> range = Observable.range(1, 10);
        range.subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
        test();
    }
}
