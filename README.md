## About

RxJavaSummary 是一个 RxJava2 实战项目。它涵盖了 RxJava2 中常用的操作符，该项目具有完善的 Demo 及良好的注释。通过学习该项目，能帮助你更好的使用 RxJava2。该项目中主要涉及的操作符：

### 创建 (Creating)

- [create][CreateOperator.java]
- [deferr][DeferOperator.java]
- [fromArray、fromIterable][FromOperator.java]
- [interval、intervalRange][IntervalOperator.java]
- [just][JustOperator.java]
- [never、empty][NeverEmptyOperator.java]
- [range][RangeOperator.java]
- [repeat、repeatUntil][RepeatOperator.java]
- [fromCallable][StartOperator.java]
- [timer][TimerOperator.java]

### 转换 (Transforming)

- [buffer][BufferOperator.java]
- [flatMap][FlatMapOperator.java]
- [map][MapOperator.java]
- [scan][ScanOperator.java]


### 过滤 (Filtering)

- [distinct、distinctUntilChanged][DistinctOperator.java]
- [filter][FilterOperator.java]
- [first、firstElement][FirstOperator.java]
- [ofType][OfTypeOperator.java]
- [sample][SampleOperator.java]
- [take、takeLast][TakeOperator.java]

### 组合 (Combining)

- [combineLatest][CombineLatest.java]
- [join][JoinOperator.java]
- [merge][MergeOperator.java]
- [startWith][StartWithOperator.java]
- [zip][ZipOperator.java]

### 错误处理 (Error Handling)

- [onErrorReturn、onErrorResumeNext、onExceptionResumeNext][ErrorOperator.java]
- [retry、retryWhen][RetryOperator.java]

### 观察者工具 (Observable Utility)

- [observerOn][ObserverOnOperator.java]
- [subscribeOn][SubscribeOnOperator.java]

### 条件 (Conditional and Boolean)

- [takeUntil][TakeUntilOperator.java]


### 数学及计算 (Mathematical and Aggregate)

- [concat、concatArray、concatArrayDelayError][ConcatOperator.java]

### 背压 (Backpressure)

- [Flowable][BackPressureOperator.java]

### Subject

- [AsyncSubject][AsyncSubjectOpe.java]
- [BehaviorSubject][BehaviorSubjectOpe.java]
- [PublishSubject][PublishSubjectOpe.java]
- [ReplaySubject][ReplaySubjectOpe.java]

### 其他观察者

- [Completable][CompletableObservable.java]
- [Maybe][MaybeObservable.java]
- [Single][SingleObservable.java]

[CreateOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/CreateOperator.java
[DeferOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/DeferOperator.java
[FromOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/FromOperator.java
[IntervalOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/IntervalOperator.java
[JustOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/JustOperator.java
[NeverEmptyOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/NeverEmptyOperator.java
[RangeOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/RangeOperator.java
[RepeatOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/RepeatOperator.java
[StartOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/StartOperator.java
[TimerOperator.java]: https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/creating/TimerOperator.java

[BufferOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/transforming/BufferOperator.java
[FlatMapOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/transforming/FlatMapOperator.java
[MapOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/transforming/MapOperator.java
[ScanOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/transforming/ScanOperator.java

[DistinctOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/filtering/DistinctOperator.java
[FilterOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/filtering/FilterOperator.java
[FirstOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/filtering/FirstOperator.java
[OfTypeOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/filtering/OfTypeOperator.java
[SampleOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/filtering/SampleOperator.java
[TakeOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/filtering/TakeOperator.java


[CombineLatest.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/combining/CombineLatest.java
[JoinOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/combining/JoinOperator.java
[MergeOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/combining/MergeOperator.java
[StartWithOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/combining/StartWithOperator.java
[ZipOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/combining/ZipOperator.java

[ErrorOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/error/ErrorOperator.java
[RetryOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/error/RetryOperator.java
 
[ObserverOnOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/utility/ObserverOnOperator.java
[SubscribeOnOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/utility/SubscribeOnOperator.java
 
[TakeUntilOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/conditional/TakeUntilOperator.java
 
[ConcatOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/mathematical/ConcatOperator.java
 
[BackPressureOperator.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/backpressure/BackPressureOperator.java
 
[AsyncSubjectOpe.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/observables/Subject/AsyncSubjectOpe.java
[BehaviorSubjectOpe.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/observables/Subject/BehaviorSubjectOpe.java
[PublishSubjectOpe.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/observables/Subject/PublishSubjectOpe.java
[ReplaySubjectOpe.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/observables/Subject/ReplaySubjectOpe.java
 
[CompletableObservable.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/observables/CompletableObservable.java
[MaybeObservable.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/observables/MaybeObservable.java
[SingleObservable.java]:https://github.com/AndyJennifer/RxJavaSummary/blob/master/src/main/java/rxjava/observables/SingleObservable.java
   
