package rxjava.observables.Subject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;

/**
 * Author:  andy.xwt
 * Date:    2019/11/20 00:28
 * Description:
 * 一个AsyncSubject只在原始Observable完成后，发射来自原始Observable的最后一个值。（如果原始Observable没有发射任何值，
 * AsyncObject也不发射任何值）它会把这最后一个值发射给任何后续的观察者。
 * 如果原始的Observable因为发生了错误而终止，AsyncSubject将不会发射任何数据，只是简单的向前传递这个错误通知。
 * https://mcxiaoke.gitbooks.io/rxdocs/content/Subject.html
 */


class AsyncSubjectOpe {

    public static void main(String[] args) {
        AsyncSubject<Integer> subject = AsyncSubject.create();
        Observable.range(1, 4).subscribe(subject);

        //第一个观察者可以收到4事件
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("first observer-->"+integer);
            }
        });

        //第二个观察者只能收到4事件
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("second observer-->"+integer);
            }
        });
    }
}
