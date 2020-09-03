package common;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 12:19
 * Description: 通用Long类型观察者
 */


public class CommonLongObserver implements Observer<Long> {


    public void onSubscribe(Disposable d) {
        System.out.println("---onSubscribe---");
    }

    @Override
    public void onNext(Long along) {
        System.out.println("onNext: " + along);
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
