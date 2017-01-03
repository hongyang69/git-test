package com.tsty.jsongson.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

public class Diaosi {
	@SerializedName("NAME1")	//使用gson转换成的json中，标识生成当前属性的key
	private String name;
	private String school;
	private boolean has_girlfriend;
	private double age;
	private Object car;
	private Object house;
	private String[] major;
	private String comment;
	private String birthday;
	private transient String ignore;	//当使用transient属性时，使用gson生成的json数据不会显示该属性
//	private String ignore;
	private Date birthdayDate;	//使用gson可以解析成日期格式
	private List<String> majorList;		//使用gson可以解析成集合类
	private Set<String> majorSet;		//使用gson可以解析成集合类
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public boolean isHas_girlfriend() {
		return has_girlfriend;
	}
	public void setHas_girlfriend(boolean has_girlfriend) {
		this.has_girlfriend = has_girlfriend;
	}
	public double getAge() {
		return age;
	}
	public void setAge(double age) {
		this.age = age;
	}
	public Object getCar() {
		return car;
	}
	public void setCar(Object car) {
		this.car = car;
	}
	public Object getHouse() {
		return house;
	}
	public void setHouse(Object house) {
		this.house = house;
	}
	public String[] getMajor() {
		return major;
	}
	public void setMajor(String[] major) {
		this.major = major;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIgnore() {
		return ignore;
	}
	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}
	public Date getBirthdayDate() {
		return birthdayDate;
	}
	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	public List<String> getMajorList() {
		return majorList;
	}
	public void setMajorList(List<String> majorList) {
		this.majorList = majorList;
	}
	public Set<String> getMajorSet() {
		return majorSet;
	}
	public void setMajorSet(Set<String> majorSet) {
		this.majorSet = majorSet;
	}
	
	
}
