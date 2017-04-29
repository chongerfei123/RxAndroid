package com.example.zl.rx_demo.java_rx;

/**
 * Created by zl on 2017/4/22.
 */

public class Test {
    public static void main(String[] args) throws Exception {
        Watched watchedxiaoming = new ConcreteWatched();
        Watcher watched1 = new ConcreteWatcher();
        Watcher watched2 = new ConcreteWatcher();
        Watcher watched3 = new ConcreteWatcher();

        watchedxiaoming.addWatcher(watched1);
        watchedxiaoming.addWatcher(watched2);
        watchedxiaoming.addWatcher(watched3);

        watchedxiaoming.notifyWatchers("sxsxs");
    }
}
