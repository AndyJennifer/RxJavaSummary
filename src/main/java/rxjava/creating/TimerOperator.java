package rxjava.creating;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2019-02-04 23:37
 * Description:Timer操作符将返回一个被观察者，且延时发送一个带0的消息
 */

public class TimerOperator {

    static void test() {
        //第一个参数：延迟时间，第二个参数：时间单位
        Observable<Long> timer = Observable.timer(1, TimeUnit.SECONDS);
        timer.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println(aLong);
            }
        });
    }

    public static void main(String[] args) {
        test();
        ThreadUtil.sleep();//这里保证虚拟机不停止运行，
    }
}