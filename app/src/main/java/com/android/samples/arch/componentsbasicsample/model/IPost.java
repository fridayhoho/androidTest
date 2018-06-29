package com.android.samples.arch.componentsbasicsample.model;


import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPost {
    @POST("posts/1")
    @FormUrlEncoded
    Observable<JSONObject> login(@FieldMap HashMap<String,String> paramsMap);
}
