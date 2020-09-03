package rxjava.filtering;

import java.util.concurrent.TimeUnit;

import common.CommonLongObserver;
import io.reactivex.Observable;
import util.ThreadUtil;

/**
 * Author:  andy.xwt
 * Date:    2020/9/3 23:29
 * Description:
 * 仅在过了一段指定的时间还没发射数据时，才发射一个数据
 *
 * @see <a href="https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Debounce.html"/>
 */


class DebounceOperator {

    /**
     * 在下面的例子中，interval 操作符发送的事件的时间间隔为600ms,debounce 接受的时间是500ms,所以能收到相应的事件
     * 如果修改为 Observable.interval(1, 200, TimeUnit.MILLISECONDS)。那么就没有任何事件发送出来
     */
    public static void main(String[] args) {
        Observable.interval(1, 600, TimeUnit.MILLISECONDS)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(new CommonLongObserver());

        ThreadUtil.sleep();
    }
}
