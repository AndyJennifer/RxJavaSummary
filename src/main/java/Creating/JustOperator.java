package Creating;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author:  andy.xwt
 * Date:    2019/1/31 15:45
 * Description:just操作符可以将多个（最多10个）时间发送给观察者
 * 注意：如果将null(空值)传递给just，将会报错，如果需要发送空数据，推荐使用Empty()操作符
 */


class JustOperator {

    static void test() {
        Observable.just("1", "2", "3").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Next: " + s);
            }
        });
    }

    public static void main(String[] args) {
        test();
    }
}
