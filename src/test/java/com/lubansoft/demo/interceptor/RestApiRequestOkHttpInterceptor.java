package com.lubansoft.demo.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RestApiRequestOkHttpInterceptor implements Interceptor {
	
	private String token;
	
    public RestApiRequestOkHttpInterceptor() {
		super();
	}
    
	public RestApiRequestOkHttpInterceptor(String token) {
		super();
		this.token = token;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request updateRequest = originalRequest.newBuilder()
                .header("token", token)
                .build();
        return chain.proceed(updateRequest);
	}
}