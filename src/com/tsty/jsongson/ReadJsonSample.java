package com.tsty.jsongson;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadJsonSample {

	public static void main(String[] args) {
	
		System.out.println(ReadJsonSample.class.getResource(""));	//path不以’/'开头时，默认是从此类所在的包下取资源；
        System.out.println(ReadJsonSample.class.getResource("/"));	//path  以’/'开头时，则是从ClassPath根下获取；
		
        System.out.println(ReadJsonSample.class.getClassLoader().getResource(""));
        System.out.println(ReadJsonSample.class.getClassLoader().getResource("/"));//null
        
        System.err.println(ReadJsonSample.class.getResource("/wangxiaoer.json"));
        
		File file = new File(ReadJsonSample.class.getResource("/wangxiaoer.json").getFile());
		String content;
		try {
			content = FileUtils.readFileToString(file);
			JSONObject jsonObject = new JSONObject(content);
			System.err.println("姓名是：" + jsonObject.getString("name"));
			System.err.println("年龄是：" + jsonObject.getDouble("age"));
			System.err.println("有没有女朋友？：" + jsonObject.getBoolean("has_girlfriend"));
			JSONArray jsonArray = jsonObject.getJSONArray("major");
			for (int i = 0; i < jsonArray.length(); i++) {
				String major = (String) jsonArray.get(i);
				System.err.println("专业-" + (i + 1) + major);
			}
			if (!jsonObject.isNull("nickname")) {
				System.err.println("小名是：" + jsonObject.getString("nickname"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
