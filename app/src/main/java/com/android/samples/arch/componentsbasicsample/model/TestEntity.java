package com.android.samples.arch.componentsbasicsample.model;

import android.util.Log;

public class TestEntity {
    public static final String TAG = "TestEntity";
    private String id;
    private String title;
    private String author;

    public void showValues(){
        Log.d(TAG, id);
        Log.d(TAG, title);
        Log.d(TAG, author);

    }
}
