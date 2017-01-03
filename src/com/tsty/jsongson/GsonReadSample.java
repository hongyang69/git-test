package com.tsty.jsongson;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.tsty.jsongson.bean.Diaosi;

public class GsonReadSample {

	public static void main(String[] args) {
		
		File file = new File(ReadJsonSample.class.getResource("/wangxiaoer.json").getFile());
		String content;
		try {
			content = FileUtils.readFileToString(file);
//			Gson gson = new Gson();
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();	//通过GsonBuilder解析成date类型的数据
			Diaosi diaosi = gson.fromJson(content, Diaosi.class);
			System.err.println(diaosi.getName());	/** 结果为null;因为name属性添加了注解@SerializedName("NAME1")；
			即json中的key"NAME1"与类文件中的"name"属性建立了映射关系；因为json文件中没有"NAME1"的key,所以生成的diaosi的name属性即为null;
			如果在json文件中，将"name"改为"NAME1",即可打印出结果 */
			System.err.println(diaosi.getSchool());
			System.err.println(diaosi.getBirthday());
			System.err.println(diaosi.getBirthdayDate().toLocaleString());
			System.err.println("[" + diaosi.getMajor()[0] + ", " + diaosi.getMajor()[1] + "]");
			System.err.println(diaosi.getMajor().getClass());
			System.err.println(diaosi.getMajorList());
			System.err.println(diaosi.getMajorList().getClass());
			System.err.println(diaosi.getMajorSet());
			System.err.println(diaosi.getMajorSet().getClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}
	
}
