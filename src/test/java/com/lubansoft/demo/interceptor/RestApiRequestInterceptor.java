package com.lubansoft.demo.interceptor;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class RestApiRequestInterceptor implements ClientHttpRequestInterceptor {
	
	private String token;
	
    public RestApiRequestInterceptor() {
		super();
	}
    
	public RestApiRequestInterceptor(String token) {
		super();
		this.token = token;
	}

	@Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add("token",token);
        return execution.execute(request, body);
    }
}