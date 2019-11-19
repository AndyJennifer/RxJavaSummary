package Observables.Subject;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Author:  andy.xwt
 * Date:    2019/11/19 23:50
 * Description:
 * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者。需要注意的是，
 * PublishSubject可能会一创建完成就立刻开始发射数据（除非你可以阻止它发生），
 * 因此这里有一个风险：在Subject被创建后到有观察者订阅它之前这个时间段内，一个或多个数据可能会丢失。
 * 如果要确保来自原始Observable的所有数据都被分发，你需要这样做：
 * 或者使用Create创建那个Observable以便手动给它引入"冷"Observable的行为（当所有观察者都已经订阅时才开始发射数据），
 * 或者改用ReplaySubject。
 * https://mcxiaoke.gitbooks.io/rxdocs/content/Subject.html
 */


class PublishSubjectOpe {

    public static void main(String[] args) {
        PublishSubject<Integer> subject = PublishSubject.create();
        //第一个观察者可以收到1，2,3事件
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("first observer-->"+integer);
            }
        });
        subject.onNext(1);
        subject.onNext(2);

        //第二个观察者只能收到3事件
        subject.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("second observer-->"+integer);
            }
        });
        subject.onNext(3);
    }
}
