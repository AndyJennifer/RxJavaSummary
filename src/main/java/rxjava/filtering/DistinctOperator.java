package rxjava.filtering;


import com.sun.prism.paint.Color;

import java.util.Arrays;
import java.util.List;

import common.CommonObserver;
import common.Shape;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author:  andy.xwt
 * Date:    2019/11/28 19:07
 * Description:抑制（过滤掉）重复的数据项
 * https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Distinct.html
 */


class DistinctOperator {


    public static List<common.Shape> mShapeLis = Arrays.asList(
            new common.Shape(Color.RED, "circular"),//红色圆形
            new common.Shape(Color.GREEN, "triangle"),//绿色三角形
            new common.Shape(Color.RED, "rhombus"),//红色菱形
            new common.Shape(Color.BLUE, "rhombus"),//蓝色菱形
            new common.Shape(Color.BLUE, "triangle"),//蓝色三角形
            new common.Shape(Color.WHITE, "triangle")//白色三角形
    );


    /**
     * Distinct的过滤规则是：只允许还没有发射过的数据项通过。
     * <p>
     * 在某些实现中，有一些变体允许你调整判定两个数据不同(distinct)的标准。还有一些实现只比较一项数据和它的直接前驱，因此只会从序列中过滤掉连续重复的数据。
     */
    public static void testDistinct() {
        //输出1，2，3
        Observable.just(1, 2, 1, 1, 2, 3)
                .distinct()
                .subscribe(new CommonObserver());

    }


    /**
     * 这个操作符有一个变体接受一个函数。这个函数根据原始Observable发射的数据项产生一个Key，然后，比较这些Key而不是数据本身，来判定两个数据是否是不同的。
     * http://reactivex.io/RxJava/javadoc/rx/Observable.html#distinct(rx.functions.Func1
     * 输出：
     * new common.Shape(Color.RED, "circular"),//红色圆形
     * new common.Shape(Color.GREEN, "triangle"),//绿色三角形
     * new common.Shape(Color.RED, "rhombus"),//红色菱形
     */
    public static void testDistinctFun() {
        Observable.create(new ObservableOnSubscribe<Shape>() {
            @Override
            public void subscribe(ObservableEmitter<Shape> emitter) throws Exception {
                for (Shape shape : mShapeLis) {
                    emitter.onNext(shape);
                }
            }
        }).distinct(new Function<Shape, String>() {
            @Override
            public String apply(Shape shape) throws Exception {
                return shape.type;//其实默认传入的是hashSet，这里返回的值就是hashset中的key
            }
        }).subscribe(new Consumer<Shape>() {
            @Override
            public void accept(Shape shape) throws Exception {
                System.out.println(shape.type + "---->" + shape.color);
            }
        });
    }

    /**
     * RxJava还是实现了一个distinctUntilChanged操作符。
     * 它只判定一个数据和它的直接前驱是否是不同的!!!!!!!!!!!!!
     * 它只判定一个数据和它的直接前驱是否是不同的!!!!!!!!!!!!!
     * 它只判定一个数据和它的直接前驱是否是不同的!!!!!!!!!!!!!
     * 和distinct(Func1)一样，根据一个函数产生的Key判定两个相邻的数据项是不是不同的
     * distinct和distinctUntilChanged默认不在任何特定的调度器上执行。
     * <p>
     * 输出：
     * new common.Shape(Color.RED, "circular"),//红色圆形
     * new common.Shape(Color.GREEN, "triangle"),//绿色三角形
     * new common.Shape(Color.RED, "rhombus"),//红色菱形
     * new common.Shape(Color.BLUE, "rhombus"),//蓝色菱形
     * new common.Shape(Color.WHITE, "triangle")//白色三角形
     */
    public static void testDistinctUntilChanged() {
        Observable.create(new ObservableOnSubscribe<Shape>() {
            @Override
            public void subscribe(ObservableEmitter<Shape> emitter) throws Exception {
                for (Shape shape : mShapeLis) {
                    emitter.onNext(shape);
                }
            }
            //判断的是上一个节点
        }).distinctUntilChanged(new Function<Shape, Color>() {
            @Override
            public Color apply(Shape shape) throws Exception {
                return shape.color;

            }
        }).subscribe(new Consumer<Shape>() {
            @Override
            public void accept(Shape shape) throws Exception {
                System.out.println(shape.type + "---->" + shape.color);
            }
        });
    }

    public static void main(String[] args) {
//        testDistinct();
        testDistinctFun();
//        testDistinctUntilChanged();
    }


}
