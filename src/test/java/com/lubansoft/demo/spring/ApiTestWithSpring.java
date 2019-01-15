package com.lubansoft.demo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.lubansoft.demo.spring.interceptor.RestApiRequestSpringRestTemplInterceptor;
import com.lubansoft.demo.util.EmptyUtils;
import com.lubansoft.demo.util.LBPropertyFile;



@RunWith(SpringJUnit4ClassRunner.class)//改变junit的运行Runner，使用spring提供的
@ContextConfiguration("classpath:applicationContext.xml")//指定spring的配置文件
public class ApiTestWithSpring {
	
	@Autowired
    private RestTemplate restTemplate;
	
	//服务器基础地址
	private static String BASE_SERVER_URL;
	
	//apikey
	private static String API_KEY;
	
	//apisecret
	private static String API_SECRET;
	
	
	static {
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
    	String url = BASE_SERVER_URL + "/token/getToken/{apikey}/{apisecret}";
        Map<String, Object> params = new HashMap<>();
        params.put("apikey", API_KEY);
        params.put("apisecret", API_SECRET);
        String token = restTemplate.getForObject(url, String.class, params);
        //2.将token加入到请求头
        if(!EmptyUtils.isBlank(token)){
			restTemplate.getInterceptors().add(new RestApiRequestSpringRestTemplInterceptor(token));//添加token
		}
    }
	
	/**
	 * 获取组织项目部信息
	 */
    @Test
    public void getOrgDeptInfos() {
    	String url = BASE_SERVER_URL + "/orgProjService/getOrgDeptList";
    	String jsonString = restTemplate.getForObject(url, String.class);
    	System.out.println("组织项目部信息:" + jsonString);
    }
    
	/**
	 * 获取项目部下工程信息
	 */
    @Test
    public void getProjListByDept() {
    	String url = BASE_SERVER_URL + "/orgProjService/getProjListByDept";
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        Map<String, Object> params = new HashMap<String, Object>();
        
        String deptId = "a1d1b0d12f8f4e748d6e646d223947c8";
        params.put("type", 0);
        List<String> deptIds = new ArrayList<String>();
        deptIds.add(deptId);
        params.put("deptIds", deptIds);
        
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params,headers);
        
        String jsonString = restTemplate.postForObject(url, request, String.class);
        
        System.out.println("项目部ID【"+deptId+"】下工程信息:" + jsonString);
    }
    
}
