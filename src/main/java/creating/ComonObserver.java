package creating;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 12:19
 * Description:
 */


class ComonObserver implements Observer<Integer> {


    public void onSubscribe(Disposable d) {
        System.out.println("onSubscribe: --->");
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("Next: " + integer);
    }

    @Override
    public void onError(Throwable error) {
        System.err.println("Error: " + error.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Sequence complete.");
    }


}
