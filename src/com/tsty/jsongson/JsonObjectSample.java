package com.tsty.jsongson;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.tsty.jsongson.bean.Diaosi;


public class JsonObjectSample {

	public static void main(String[] args) {
//		jSONObjectSample();
//		createJsonByMap();
		createJsonByBean();
	}

	private static void jSONObjectSample() {
		JSONObject jsonObject = new JSONObject();
		Object nullObj = null;
		try {
			jsonObject.put("name", "王小二");
			jsonObject.put("age", 25.2);
			jsonObject.put("birthday", "1990-01-01");
			jsonObject.put("school", "蓝翔");
			jsonObject.put("major", new String[]{"理发","挖掘机"});
			jsonObject.put("has_girlfriend", false);
			jsonObject.put("car", nullObj);
			jsonObject.put("house", nullObj);
			jsonObject.put("comment", "这是一个注释");
			
			System.err.println(jsonObject.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createJsonByBean() {
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
		
		System.err.println(new JSONObject(diaosi));
			
			
	}

	private static void createJsonByMap(){
		Map<String, Object> jsonMap = new HashMap<String, Object>(); 
		Object nullObj = null;
		jsonMap.put("name", "王小二");
		jsonMap.put("age", 25.2);
		jsonMap.put("birthday", "1990-01-01");
		jsonMap.put("school", "蓝翔");
		jsonMap.put("major", new String[]{"理发","挖掘机"});
		jsonMap.put("has_girlfriend", false);
		jsonMap.put("car", nullObj);
		jsonMap.put("house", nullObj);
		jsonMap.put("comment", "这是一个注释");
		
		System.err.println(new JSONObject(jsonMap));

	}
}
