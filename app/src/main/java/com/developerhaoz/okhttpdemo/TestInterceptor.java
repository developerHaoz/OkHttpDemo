package com.developerhaoz.okhttpdemo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by developerHaoz on 2017/8/5.
 */

public class TestInterceptor extends Interceptor {

    Request
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
