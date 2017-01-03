package com.tsty.jsongson;

import java.lang.reflect.Field;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tsty.jsongson.bean.Diaosi;

public class GsonCreateSample {

	public static void main(String[] args) {
		Diaosi diaosi = new Diaosi();
		
		diaosi.setName("王小二");
		diaosi.setAge(25.2);
		diaosi.setBirthday("1990-01-01");
		diaosi.setSchool("蓝翔");
		diaosi.setMajor(new String[]{"理发","挖掘机"});
		diaosi.setHas_girlfriend(false);
		diaosi.setCar(null);
		diaosi.setHouse(null);
		diaosi.setComment("这是一个注释");
		diaosi.setIgnore("不显示");
		
		//1. 使用Gson生成的json数据是未格式化的json数据
		Gson gson = new Gson();
		System.err.println(gson.toJson(diaosi));
		
		//2. 使用GsonBuilder可以生成格式化的json数据
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();	//设置格式化的打印输出
		gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy() {
			
			//针对json的key，实行个性化处理；当该方法与@SerializedName注解一起使用时，最后以注解为准
			public String translateName(Field paramField) {
				if ("school".equals(paramField.getName())) {
					return "SCHOOL";
				}
				if ("name".equals(paramField.getName())) {
					return "NAME";
				}
				return paramField.getName();
			}
		});
		gson = gsonBuilder.create();
		System.err.println(gson.toJson(diaosi));
		
	}
	
}
