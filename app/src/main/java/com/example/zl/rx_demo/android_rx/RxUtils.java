package com.example.zl.rx_demo.android_rx;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zl on 2017/4/22.
 */

public class RxUtils {
    private static final String TAG = RxUtils.class.getSimpleName();

    /**
     * 使用create方式
     */
    public static void createObserable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("hello");
                    subscriber.onNext("hi");
                    subscriber.onCompleted();
                }
            }
        });

        Subscriber<String> showsub = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "result--" + s);
            }
        };
        observable.subscribe(showsub);//关联被观察者
    }

    /**
     * create 第二种方式，连着写
     */
    public static void createPrint() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(1);
                    subscriber.onNext(2);
                    subscriber.onNext(3);
                    subscriber.onNext(4);
                    subscriber.onNext(5);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "result--" + integer);
            }
        });
    }

    /**
     * 返回的对象一般都是数组类型
     * （把数组或者array全部发过去，而不用自己遍历数组，一个个发送）
     */
    public static void from() {
        Integer[] item = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Observable observable = Observable.from(item);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG, o.toString());
            }
        });
    }

    /**
     * 指定某一时刻发送数据
     */
    public static void interval() {
        Integer[] item = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Observable observable = Observable.interval(1, 1, TimeUnit.SECONDS);//每隔一秒发送数据
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG, o.toString());
            }
        });
    }

    /**
     * 处理数组数据
     */
    public static void just() {
        Integer[] item1 = {1, 3, 5, 7, 9};
        Integer[] item2 = {2, 4, 6, 8, 0};
        Observable observable = Observable.just(item1, item2);
        observable.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(Integer[] o) {
                for (int i = 0; i < o.length; i++) {
                    Log.i(TAG, o[i] + "");
                }
            }
        });
    }

    /**
     * 输出一个范围的数据
     */
    public static void range() {
        Observable observable = Observable.range(1, 40);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                Log.i(TAG, o + "");
            }
        });
    }

    /**
     * 过滤数据
     * 被观察者向观察者发送的消息，先会被过滤一遍才发送
     */
    public static void filter() {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer o) {
                return o < 5;
            }
        }).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer o) {
                Log.i(TAG, o + "");
            }
        });
    }

    //重复发送5次
    //observable.repeat(5);

    //取前4个
    //observable.take(4);

    //去除重复的数据
    //observable.distinct();

    //去除相邻的重复的数据
    //eg: 22 22 23 22 -> 22 23 22 只有相邻的数据重复了，才会被去除
    //observable.distinctUtilChanged();

    //只发送第一个或最后一个
    //first和last

    //跳过前两个或 跳过最后两个
    //skip（2）和skiplast（2）

    //只发送第几个
    //ElementAt

    //延时一段时间发送
    //timer（3000，TimeUtil.Milliseconds）

    //使用一个时间间隔，在时间间隔到了时，被观察者发送最近一次的数值（原始数据太多了，并不需要那么多，手动降低发送频率）
    //sample

    //在一定时间间隔内，被观察者如果没有发送数据，就会调用onError
    //timeout

    //在一定时间间隔内，被观察者如果没有发送数据，就会自动发送上一次的数据
    //debounce

    /**
     * 关于线程
     * Schedulers.immecliate()在当前线程执行
     * Schedulers.newThread()新一个线程
     * AndroidSchedulers.mainThread()在Android的UI线程中
     * Schedulers.io() i/o操作所使用的线程，行为模式与newThread差不多，但比newThread更有效率
     * Schedulers.computation() cpu密集计算使用的线程
     *
     * subscribeOn()改变调用它之前代码的线程
     * observeOn()改变调用它之后代码的线程
     * Observable
     *       .map                    // 操作1
     *       .flatMap                // 操作2
     *       .subscribeOn(io)
     *       .map                    //操作3
     *       .observeOn(main)
     *       .map                    //操作5
     *       .flatMap                //操作6
     *       .subscribeOn(io)        //!!特别注意
     *      .subscribe(handleData)
     *
     *
     *      操作1，操作2是在io线程上，因为之后subscribeOn切换了线程
     *      操作3，操作4也是在io线程上，因为在subscribeOn切换了线程之后，并没有发生改变。
     *      操作5，操作6是在main线程上，因为在他们之前的observeOn切换了线程。
     *      特别注意那一段，对于操作5和操作6是无效的，observeOn之后，不可再调用subscribeOn 切换线程
     *
     */

    public static void aboutThread() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("");
                //log一下被观察者所在的线程
                Log.i("sout", "observable : "+Thread.currentThread().getName());
                subscriber.onCompleted();
            }
        });

        //不指定线程的情况下，在哪个线程中调用subscribe（），
        // 就在那个线程中成产事件和消费事件
        //Schedulers.immediate()表示在当前的线程执行，设置这个话跟什么都不设置的效果是一样的
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                //log观察者所在的线程
                Log.i("sout", "fangshi1 : "+Thread.currentThread().getName());
            }
        });

        //让观察者在新线程中执行 observeOn(Schedulers.newThread())
        observable.observeOn(Schedulers.newThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                //log观察者所在的线程
                Log.i("sout", "fangshi2 : "+Thread.currentThread().getName());
            }
        });

    }

    /**
     * map数据类型转换
     * （当被观察者发送的数据 和 观察者想要接受的数据类型不一样的时候使用    或者在 被观察者数据的发送 和 观察者接受 之间 进行二次加工(这种功能filter也可以实现)）
     * observable.map
     */
    public static void mapTransform(){
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onNext(4);
                subscriber.onCompleted();
            }
        });
        observable.map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {

                return integer.toString();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("sout",s);
            }
        });
    }

    /**
     * flatmap数据类型转换
     * 应用场景 ： 数组—》变量  如：String[] -> String
     * observable.flatMap
     */
    public static void flatmapTransform(){
        Observable<String[]> observable = Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                String[] s = {"a","b","c","d"};
                subscriber.onNext(s);
                subscriber.onCompleted();
            }
        });
        observable.flatMap(new Func1<String[], Observable<String>>() {
            @Override
            public Observable<String> call(String[] strings) {
                return Observable.from(strings);
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("sout",s);
            }
        });
    }
}
