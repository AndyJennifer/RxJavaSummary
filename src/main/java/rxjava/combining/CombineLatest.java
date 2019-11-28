package rxjava.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2019-02-08 14:29
 * Description:combineLatest操作符将两个被观察者的发送时间按照最近发送的事件进行 合并!!!
 */

public class CombineLatest {

    /**
     * 下面的例子中
     * Observable1:12|34|5 |
     * Observable2: 5| 6| 7|89|10
     * 合并的结果为 15 25 35 46 56 57 58 59 510
     */
    static void test() {
        Observable.combineLatest(
                Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
                , Observable.intervalRange(5, 6, 0, 2, TimeUnit.SECONDS)
                , new BiFunction<Long, Long, String>() {
                    @Override
                    public String apply(Long aLong, Long aLong2) throws Exception {
                        return aLong + "-->" + aLong2;
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
        ThreadUtil.sleep();//这里保证虚拟机不停止运行，
    }
}
