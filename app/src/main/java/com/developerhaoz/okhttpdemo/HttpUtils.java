package com.developerhaoz.okhttpdemo;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by developerHaoz on 2017/8/4.
 */

public class HttpUtils {

    private final OkHttpClient mClient = new OkHttpClient();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://www.baidu.com/")
                .build();
    }
}




















