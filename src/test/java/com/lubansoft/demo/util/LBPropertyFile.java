package com.lubansoft.demo.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class LBPropertyFile {

	private static LBPropertyFile instance=new LBPropertyFile();
	
	private LBPropertyFile(){}
	
	public static LBPropertyFile getInstance(){
		return instance;
	}
	
	/**
	 * 从资源文件中读取属性值
	 */
	public String getProperty(String fileName, String propertyName)
			throws Exception {
		Properties prop = new Properties();
		try {
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(fileName);
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");

			prop.load(reader);
			if (reader != null) {
				is.close();
				reader.close();
			}

		} catch (Exception e) {

			throw new Exception("读取资源文件【" + fileName + "】过程中发生错误："
					+ e.getMessage());

		}

		return prop.getProperty(propertyName);
	}
	
	/**
	 * 处理URL路径，判断最后是否以"/"结尾，若是，去除"/"
	 */
	public String removeLastSlash(String url){
		String rst=url.trim().replaceAll("\\\\", "/");
		if(rst.endsWith("/")){
			return rst.substring(0, rst.length()-1);
		}else{
			return rst;
		}
	}
	
	/**
	 * 去除字符串开头处的"/"
	 */
	public String removeFirstSlash(String str){
		String rst=str.trim().replaceAll("\\\\", "/");
		if(rst.startsWith("/")){
			return rst.substring(1, rst.length());
		}else{
			return rst;
		}
	}
	
	/**
	 * 为字符串开头增加"\"
	 */
	public String prependSlash(String str){
		String rst=str.trim().replaceAll("\\\\", "/");
		if(rst.startsWith("/")){
			return rst;
		}else{
			return "/"+rst;
		}
	}
	
	/**
	 * 为字符串最后加上"/"
	 */
	public String appendSlash(String url){
		String rst=url.trim().replaceAll("\\\\", "/");
		
		if(rst.endsWith("/")){
			return rst;
		}else{
			return rst+"/";
		}
	}
}
