package Observables.Subject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.ReplaySubject;

/**
 * Author:  andy.xwt
 * Date:    2019/11/19 23:59
 * Description:
 * 该Subject会接收数据，当被订阅时，将所有接收到的数据全部发送给订阅者。
 * ReplaySubject会发射所有来自原始Observable的数据给观察者，无论它们是何时订阅的。
 * 也有其它版本的ReplaySubject，在重放缓存增长到一定大小的时候或过了一段时间后会丢弃旧的数据（原始Observable发射的）。
 * https://mcxiaoke.gitbooks.io/rxdocs/content/Subject.html
 */


class ReplaySubjectOpe {

    public static void main(String[] args) {
        ReplaySubject<Integer> subject = ReplaySubject.create();
        Observable.range(1, 4).subscribe(subject);

        //第一个观察者可以收到1，2,3,4事件
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("first observer-->"+integer);
            }
        });

        //第二个观察者只能收到1,2,3,4事件
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("second observer-->"+integer);
            }
        });

    }
}
