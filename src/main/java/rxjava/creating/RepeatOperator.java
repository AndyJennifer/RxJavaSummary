package rxjava.creating;

import io.reactivex.Observable;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;

/**
 * Author:  andy.xwt
 * Date:    2019/2/1 11:17
 * Description:
 * repeat:运算符允许重新发送一系列事件
 * repeatUntil:直到某个条件满足才停止发送
 */


class RepeatOperator {

    static void testRepeat() {
        //repeat执行次数
        Observable.just("1").repeat(3).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    static void testRepeatUntil() {
        Observable.just("2").repeatUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Exception {
                int rand = (int) (Math.random() * 10);
                return rand == 5;//直到等于5才停止
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }


    public static void main(String[] args) {
//        testRepeat();
        testRepeatUntil();
    }
}
