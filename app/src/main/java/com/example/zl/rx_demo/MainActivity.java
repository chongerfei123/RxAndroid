package com.example.zl.rx_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.zl.rx_demo.android_rx.RxUtils;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void create(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.createObserable();
    }

    public void create2(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.createPrint();
    }

    public void create3(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.from();
    }

    public void create4(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.interval();
    }

    public void create5(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.just();
    }

    public void create6(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.range();
    }

    public void create7(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.filter();
    }

    public void create8(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.aboutThread();
    }

    public void create9(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.mapTransform();
    }

    public void create10(View v) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.flatmapTransform();
    }
}
