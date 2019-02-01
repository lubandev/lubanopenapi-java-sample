# 功能介绍

本示例介绍使用Java调用鲁班开放平台接口,提供两种方式：<br>
1、Junit结合轻量级网络请求框架OkHttp调用<br>
2、Junit结合Spring-test、Spring RestTemplete调用(适合熟悉spring框架的开发人员)

# 运行必须的组件

* Maven
* JDK7

# Maven依赖（第一种非spring方式）

```xml
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<!-- junit版本号 -->  
        <junit.version>4.12</junit.version>  
	</properties>

	<dependencies>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.16</version>
		</dependency>
		<!-- JUnit单元测试框架 -->
		<dependency>  
		    <groupId>junit</groupId>  
		    <artifactId>junit</artifactId>  
		    <version>${junit.version}</version>  
		    <scope>test</scope>  
		</dependency> 
				<dependency>
		   <groupId>com.squareup.okhttp3</groupId>
		   <artifactId>okhttp</artifactId>
		   <version>3.12.1</version>
		</dependency>
	</dependencies>
```
# Maven依赖（第二种结合spring方式）
```xml
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<spring.version>4.1.6.RELEASE</spring.version>
		<!-- junit版本号 -->  
        <junit.version>4.12</junit.version>  
	</properties>

	<dependencies>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.16</version>
		</dependency>
		<!-- JUnit单元测试框架 -->
		<dependency>  
		    <groupId>junit</groupId>  
		    <artifactId>junit</artifactId>  
		    <version>${junit.version}</version>  
		    <scope>test</scope>  
		</dependency> 
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<!-- spring对测试框架的简单封装功能 -->   
		<dependency>
		    <groupId>org.springframework</groupId>  
		    <artifactId>spring-test</artifactId>  
		    <version>${spring.version}</version>  
		    <scope>test</scope>  
		</dependency>
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-core</artifactId>
		    <version>2.9.5</version>
		</dependency>
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.9.5</version>
		</dependency>
		
		<dependency>
		   <groupId>com.squareup.okhttp3</groupId>
		   <artifactId>okhttp</artifactId>
		   <version>3.12.1</version>
		</dependency>
	</dependencies>
```
# 如何在本地运行？

使用git下载源代码
```bash
git clone https://github.com/lubandev/lubanopenapi-java-sample.git
```
修改config.properties
```
#实际使用需要替换以下配置

#服务器基础地址
server.baseurl=http://192.168.3.231:8081/openapi

#apikey
apikey=96e79218965eb72c92a549dd5a330113

#apisecret
apisecret=7f4420de6a75f7afbfbe3d0de7db7997
```
直接运行ApiTest/ApiTestWithSpring类中的junit单元测试用例
```java
    //非spring方式
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
```
```java
    //spring方式
    @Test
    public void getOrgDeptInfos() {
    	String url = BASE_SERVER_URL + "/orgProjService/getOrgDeptList";
    	String jsonString = restTemplate.getForObject(url, String.class);
    	System.out.println("组织项目部信息:" + jsonString);
    }
```
