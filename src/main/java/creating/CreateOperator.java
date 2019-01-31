package creating;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 11:44
 * Description:
 * 通过编程调用Observer方法从头开始创建Observable
 * <p>
 * 您可以使用create操作符从头开始创建一个observable。您向这个操作符传递一个接受观察者作为其参数的函数。
 * 编写这个函数，使其作为一个可观测的——通过适当地调用观察者的onNext、onError和onCompleted方法。
 * <p>
 * 格式良好的有限可观测对象必须尝试调用一次观察者的onComplete方法或其onerror方法，并且此后不得尝试调用观察者的任何其他方法。
 */


class CreateOperator {

    private static void test() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                try {
                    for (int i = 1; i < 5; i++) {
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
        test();
    }
}
