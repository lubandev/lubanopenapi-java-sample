package com.lubansoft.demo;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.junit.Before;
import org.junit.Test;

import com.lubansoft.demo.interceptor.RestApiRequestOkHttpInterceptor;
import com.lubansoft.demo.util.EmptyUtils;
import com.lubansoft.demo.util.LBPropertyFile;



public class ApiTest {
	
	//服务器基础地址
	private static String BASE_SERVER_URL;
	
	//apikey
	private static String API_KEY;
	
	//apisecret
	private static String API_SECRET;
	
	//网络框架方便rest接口调用
	private static OkHttpClient client;
	
	//设置contentType为application/json
	private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	
	
	static {
		//读取配置参数初始化
		try {
			LBPropertyFile instance = LBPropertyFile.getInstance();
			String serverBaseUrl = instance.getProperty("config.properties", "server.baseurl");
			String apikey = instance.getProperty("config.properties", "apikey");
			String apisecret = instance.getProperty("config.properties", "apisecret");
			if(EmptyUtils.isBlank(serverBaseUrl) || EmptyUtils.isBlank(apikey) || EmptyUtils.isBlank(apisecret)){
				throw new Exception("配置文件config.properties中的配置错误,请检查！");
			}
			BASE_SERVER_URL = instance.appendSlash(serverBaseUrl) + "rs";
			API_KEY = apikey;
			API_SECRET = apisecret;
			client = new OkHttpClient();
		} catch (Exception e) {
			throw new RuntimeException("读取配置文件config.properties失败！",e);  
		}
	}
	

	/**
	 * 获取token并将token加入到请求头
	 */
	@Before
	public void getToken() throws Exception {
		//1.获取token
    	String url = BASE_SERVER_URL + "/token/getToken/"+API_KEY+"/"+API_SECRET;
    	Request request = new Request.Builder()
        					.url(url)
        					.build();
    	Response response = client.newCall(request).execute();
    	if(response.isSuccessful()){
    		String token = response.body().string();
    		//2.通过拦截器将token加入到请求头
            if(!EmptyUtils.isBlank(token)){
            	client = client.newBuilder().addInterceptor(new RestApiRequestOkHttpInterceptor(token)).build();
    		}
    	}
        
    }
	
	/**
	 * 获取组织项目部信息
	 */
    @Test
    public void getOrgDeptInfos() throws IOException {
    	String url = BASE_SERVER_URL + "/orgProjService/getOrgDeptList";
    	Request request = new Request.Builder()
							.url(url)
							.build();
    	Response response = client.newCall(request).execute();
    	if(response.isSuccessful()){
    		System.out.println("组织项目部信息:" + response.body().string());
    	}
    }
    
	/**
	 * 获取项目部下工程信息
	 */
    @Test
    public void getProjListByDept() throws IOException {
    	String url = BASE_SERVER_URL + "/orgProjService/getProjListByDept";
    	String jsonParam = "{\"type\":0,\"deptIds\":[\"a1d1b0d12f8f4e748d6e646d223947c8\"]}";
    	RequestBody body = RequestBody.create(JSON, jsonParam);
    	Request request = new Request.Builder()
    	      .url(url)
    	      .post(body)
    	      .build();
    	Response response = client.newCall(request).execute();
    	if(response.isSuccessful()){
    		System.out.println("组织项目部信息:" + response.body().string());
    	}
    }
    
}
