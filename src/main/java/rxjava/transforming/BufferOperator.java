package rxjava.transforming;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author:  andy.xwt
 * Date:    2019-02-05 23:23
 * Description:range操作符是缓存一定量的数据然后发送，
 */

public class BufferOperator {

    static void test() {
        Observable.range(1, 10).buffer(3).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                System.out.println("拿缓存");
                for (Integer integer : integers) {
                    System.out.println(integer);
                }
            }
        });

    }

    public static void main(String[] args) {
        test();
    }
}
