package com.boot.batch.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("dto")
public class BatchDto {
	private String username;
	private String password;
	private Date regdate;
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	

}
