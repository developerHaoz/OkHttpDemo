package com.developerhaoz.okhttpdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by developerHaoz on 2017/8/4.
 */

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private Button mBtnTest;
    private TextView mTvShowInfo;
    private OkHttpClient mClient = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mBtnTest = (Button) findViewById(R.id.test_btn_test);
        mTvShowInfo = (TextView) findViewById(R.id.test_tv_test);
        URLConnection.getFileNameMap();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

        mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Observable.create(new Observable.OnSubscribe<String>() {

                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        OkHttpClient okhttpClient = new OkHttpClient();
                        RequestBody requestBody = new RequestBody() {
                            @Nullable
                            @Override
                            public MediaType contentType() {
                                return MEDIA_TYPE_MARKDOWN;
                            }

                            @Override
                            public void writeTo(BufferedSink sink) throws IOException {
                                sink.writeUtf8("Numbers\n");
                                sink.writeUtf8("-------\n");
                                for (int i = 0; i <= 997; i++) {
                                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                                }
                            }
                        };

                        Request request = new Request.Builder()
                                .url("https://api.github.com/markdown/raw")
                                .post(requestBody)
                                .build();

                        try {
                            Response response = okhttpClient.newCall(request).execute();
                            subscriber.onNext(response.body().string());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {
                                mTvShowInfo.setText(s);
                            }
                        });

            }
        });
    }

    private String factor(int n){
        for (int i = 2; i < n; i++) {
            int x = n / i;
            if(x * i == n) return factor(x) + " x " + i;
        }
        return Integer.toString(n);
    }
}















