package com.ctf.entity;

/**
 * 客户实体
 * @author Administrator
 *
 */
public class Customer {

	private Integer id; // 编号
	private String khno; // 客户编号 动态生成
	private String name; // 客户名称
	private String address; // 客户地址
	private String postCode; // 邮政编码
	private String phone; // 联系电话
	private Integer companyID;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getKhno() {
		return khno;
	}
	public void setKhno(String khno) {
		this.khno = khno;
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
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getCompanyID() {
		return companyID;
	}
	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}
	
}
