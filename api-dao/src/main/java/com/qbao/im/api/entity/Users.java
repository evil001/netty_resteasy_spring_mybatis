package com.qbao.im.api.entity;

import com.alibaba.fastjson.JSONObject;

//import javax.ws.rs.FormParam;
import java.io.Serializable;

public class Users {

//	@FormParam("id")
	private Long id;

//	@FormParam("userName")
	private String userName;

//	@FormParam("age")
	private int age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
