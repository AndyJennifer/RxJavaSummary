package rxjava.utility;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2019/11/18 14:55
 * Description: http://reactivex.io/documentation/operators/observeon.html
 * observeOn 规定了观察者接受事件的运行线程
 */


class ObserverOnOperator {

    /**
     * 若Observable.observeOn（）多次指定观察者 接收 & 响应事件的线程，
     * 则每次指定均有效，即每指定一次，就会进行一次线程的切换
     */
    public static void main(String[] args) {
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) {
                        for (int i = 1; i < 5; i++) {
                            emitter.onNext(i);
                        }
                        System.out.println("线程名称：" + ThreadUtil.getThreadName());
                        emitter.onComplete();

                    }

                })
                .observeOn(Schedulers.io())//使用observeOn将会影响后面中操作符的运行所在线程
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        System.out.println("第一次observeOn：" + ThreadUtil.getThreadName());
                        return integer + 1;
                    }
                })
                .observeOn(Schedulers.computation())//只有最后一次有效
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("第二次observeOn：" + ThreadUtil.getThreadName());
                    }
                });
        ThreadUtil.sleep();
    }

}
