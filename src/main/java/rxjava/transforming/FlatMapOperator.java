package rxjava.transforming;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author:  andy.xwt
 * Date:    2019-02-04 23:51
 * Description:
 * FlatMap操作符使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，
 * 这个函数返回一个本身也发射数据的Observable，然后FlatMap合并这些Observables发射的数据，
 * 最后将合并后的结果当做它自己的数据序列发射。
 */

public class FlatMapOperator {

    static void test() {
        Observable.range(1, 3).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer s) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add(s + "事件" + "变换的第" + i + "个事件");
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    public static void main(String[] args) {
        test();
    }
}
