package Filtering;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 13:03
 * Description:ofType操作符会过滤掉不满足相应class类型的事件，内部原理其实就是filter(Functions.isInstanceOf(clazz)).cast(clazz);
 */

public class OfTypeOperator {

    static void test() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("xixi");
                emitter.onNext(1);
                emitter.onNext(2);
            }
        }).ofType(String.class).subscribe(new Consumer<String>() {
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
