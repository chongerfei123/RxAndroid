package com.example.zl.rx_demo.java_rx;

/**
 * Created by zl on 2017/4/22.
 */

public interface Watched {
    public void addWatcher(Watcher watcher);
    public void removeWatcher(Watcher watcher);
    public void notifyWatchers(String str);
}
