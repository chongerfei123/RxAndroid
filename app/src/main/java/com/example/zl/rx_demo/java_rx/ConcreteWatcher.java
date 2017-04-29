package com.example.zl.rx_demo.java_rx;

/**
 * Created by zl on 2017/4/22.
 */

public class ConcreteWatcher implements Watcher {

    @Override
    public void update(String str) {

        System.out.println(str);
    }
}
