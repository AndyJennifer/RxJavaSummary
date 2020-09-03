package common;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 12:19
 * Description: 通用Integer类型观察者
 */


public class CommonIntegerObserver implements Observer<Integer> {


    public void onSubscribe(Disposable d) {
        System.out.println("---onSubscribe---");
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("onNext: " + integer);
    }

    @Override
    public void onError(Throwable error) {
        System.err.println("onError: " + error.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("---onComplete---");
    }


}
