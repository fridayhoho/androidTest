package com.android.samples.arch.componentsbasicsample.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.samples.arch.componentsbasicsample.R;
import com.android.samples.arch.componentsbasicsample.model.CONSTANTS;
import com.android.samples.arch.componentsbasicsample.model.GetRequestInterface;
import com.android.samples.arch.componentsbasicsample.model.GetRequestInterfaceRX;
import com.android.samples.arch.componentsbasicsample.model.IPost;
import com.android.samples.arch.componentsbasicsample.model.TestEntity;
import com.android.samples.arch.componentsbasicsample.model.Translation;
import com.android.samples.arch.componentsbasicsample.net.NetUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "RxAndroidSamples";

    private final CompositeDisposable disposables = new CompositeDisposable();
    private static Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findViewById(R.id.btnT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getWithRxjava();
                doPost();
//                onRunSchedulerExampleButtonClicked();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
        Log.d(TAG, "onDestory clear");
    }

    void onRunSchedulerExampleButtonClicked() {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override
                    public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                    }
                }));
    }

    Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                // Do some long running operation
                request();
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

    // 使用Retrofit封装的方法
    private void request() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .client(NetUtil.getDebugOkClient())
                .baseUrl("http://fy.iciba.com/") //http://fy.iciba.com/
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequestInterface request = retrofit.create(GetRequestInterface.class);
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                Log.e(TAG, "onResponse: " + response.body());

                Translation translation = response.body();
                translation.show();
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {

            }
        });
    }

    public void getWithRxjava() {
        //步骤4：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .client(NetUtil.getDebugOkClient())
                .baseUrl("http://10.30.14.164:3000") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // 步骤5：创建 网络请求接口 的实例
        GetRequestInterfaceRX request = retrofit.create(GetRequestInterfaceRX.class);

        // 步骤6：采用Observable<...>形式 对 网络请求 进行封装
        Observable<Translation> observable = request.getCall();

        // 步骤7：发送网络请求
        observable.subscribeOn(Schedulers.io())               // 在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 回到主线程 处理请求结果
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Translation result) {
                        // 步骤8：对返回的数据进行处理
                        result.show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "请求失败");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "请求成功");
                    }
                });
    }

    /**
     * 初始化retrofit
     */
    public static Retrofit RetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(CONSTANTS.BASE_URL)
                    //添加Gson支持
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加Retrofit对Rxjava的支持
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //配置OkHttp
                    .client(NetUtil.getDebugOkClient()).build();
            return retrofit;
        } else {
            return retrofit;
        }
    }

    public void doPost() {
        HashMap map = new HashMap<String, String>();
        map.put("tid", "login");
        map.put("userName", "xiaoming");
        map.put("passWd", "qwer1234");
        IPost loginService = RetrofitInstance().create(IPost.class);
        loginService.login(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestEntity>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestEntity value) {
                        value.showValues();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
