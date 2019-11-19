package Observables.Subject;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Author:  andy.xwt
 * Date:    2019/11/20 00:18
 * Description:
 * 当Observer订阅了一个BehaviorSubject，
 * 它一开始就会释放Observable最近释放的一个数据对象，当还没有任何数据释放时，它则是一个默认值。接下来就会释放Observable释放的所有数据。
 * 如果Observable因异常终止，BehaviorSubject将不会向后续的Observer释放数据，但是会向Observer传递一个异常通知。
 * https://mcxiaoke.gitbooks.io/rxdocs/content/Subject.html
 */


class BehaviorSubjectOpe {

    public static void main(String[] args) {
        BehaviorSubject<Integer> subject = BehaviorSubject.createDefault(0);

        //第一个观察者可以收到0,1，2,3,4事件
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("first observer-->" + integer);
            }
        });

        subject.onNext(1);
        subject.onNext(2);

        //第一个观察者可以收到2,3,4事件
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("second observer-->" + integer);
            }
        });
        subject.onNext(3);
        subject.onNext(4);


    }
}
