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
 * Description:flatMap操作符会将被观察者发送的事件，拆分或者合并成新的事件并发送给观察者。
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
