package com.ctf.entity;

/**
 * @author Administrator
 *
 */
public class Company {

	private Integer id; // 编号
	private String name; // 客户名称
	private String address; // 客户地址
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
