package Filtering;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author:  andy.xwt
 * Date:    2019-02-06 00:20
 * Description:first firstElement 都是取发送的第一个有效事件
 */

public class FirstOperator {

    static void testFirst() {
        Observable.range(1, 10).first(0).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    static void firstElement() {
        Observable.range(1, 10).firstElement().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    public static void main(String[] args) {
//        testFirst();
        firstElement();
    }
}
