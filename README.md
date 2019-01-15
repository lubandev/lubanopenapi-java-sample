功能介绍
========
本示例介绍使用Java调用鲁班开放平台接口,提供两种方式：<br>
1、Junit结合轻量级网络请求框架OkHttp调用<br>
2、Junit结合Spring-test、Spring RestTemplete调用(适合熟悉spring框架的开发人员)<br>
运行必须的组件
========
* Maven
* JDK7<br>

Maven依赖（第一种非spring方式）
========
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
Maven依赖（第二种结合spring方式）
========
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
