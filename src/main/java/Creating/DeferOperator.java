package Creating;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import Common.CommonObserver;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 12:10
 * Description:直到有观察者订阅时，才动态的创建被观察者
 * 延迟操作符等待直到观察者订阅它，在某些情况下，等到最后一分钟（即订阅时间）生成Observable可以确保该Observable包含最新的数据。
 */


class DeferOperator {

    static int i = 0;

    private static void test() {
        Observable<Integer> defer = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(i);
            }
        });

        System.out.println("订阅之间i的值---->" + i);

        //打印订阅后改变的值
        i = 10;
        defer.subscribe(new CommonObserver());
    }

    public static void main(String[] args) {
        test();
    }
}
