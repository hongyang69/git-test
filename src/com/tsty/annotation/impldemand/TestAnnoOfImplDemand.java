package com.tsty.annotation.impldemand;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestAnnoOfImplDemand {

	public static void main(String[] args) {
		Filter f1 = new Filter();
		f1.setId(10);	//查询ID为10的用户
		f1.setUserName("tsty");
		
		Filter f2 = new Filter();
		f2.setUserName("lucy");//模糊查询用户名为lucy的用户
		
		Filter f3 = new Filter();
		f3.setEmail("tstyone@qq.com,tstytwo@qq.com,tstythree@qq.com");//查询邮箱为其中任意一个的用户
		
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3 = query(f3);
		
		System.err.println(sql1);
		System.err.println(sql2);
		System.err.println(sql3);
		
		Filter1 f11 = new Filter1();
		f11.setAmount(100);
		f11.setName("技术部");
		String sql11 = query(f11);
		System.err.println(sql11);
	}
	
//	private static String query(Filter f){
	private static String query(Object f){
		StringBuilder sbBuilder = new StringBuilder();
		//1. 获取到class
		Class kClass = f.getClass();
		//2. 获取到table的名字
		boolean isExisted = kClass.isAnnotationPresent(Table.class);
		if (!isExisted) {
			 return null;
		}
		Table table = (Table) kClass.getAnnotation(Table.class);
		String tableName = table.value();
		sbBuilder.append("select * from ").append(tableName).append(" where 1 = 1");
		//3. 遍历所有的字段
		Field[] fArray = kClass.getDeclaredFields();
		for (Field field : fArray) {
			//4.处理每个字段对应的sql
			//4.1 拿到字段名
			boolean isFExisted = field.isAnnotationPresent(Column.class);
			if (!isFExisted) {
				continue;
			}
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			//4.2 拿到字段的值
			String filedName = field.getName();
			String getMethodName = "get" + filedName.substring(0, 1).toUpperCase() + 
					filedName.substring(1);
			Object filedValue = null;
			try {
				Method getMethod = kClass.getMethod(getMethodName);
			    filedValue = getMethod.invoke(f, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 			
			//4.3 拼装sql
			if (filedValue == null || 
					(filedValue instanceof Integer && (Integer)filedValue == 0)) {
				continue;
			}
			sbBuilder.append(" and ").append(columnName);
			if (filedValue instanceof String) {
				if (((String) filedValue).contains(",")) {
					String[] values = ((String) filedValue).split(",");
					sbBuilder.append(" in (");
					for (String value : values) {
						sbBuilder.append("'").append(value).append("'").append(",");  
					}
					sbBuilder.deleteCharAt(sbBuilder.length() - 1);
					sbBuilder.append(")");
				} else {
					sbBuilder.append(" = ").append("'").append(filedValue).append("'");  
				}
			} else if (filedValue instanceof Integer) {
				sbBuilder.append(" = ").append(filedValue);
			}
//			sbBuilder.append(" and ").append(columnName).append(" = ").append(filedValue);
		}
		return sbBuilder.toString();
		
	}
	
}
