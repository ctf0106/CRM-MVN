package com.ctf.entity;

/**
 * �ͻ�ʵ��
 * @author Administrator
 *
 */
public class Customer {

	private Integer id; // ���
	private String khno; // �ͻ���� ��̬����
	private String name; // �ͻ�����
	private String address; // �ͻ���ַ
	private String postCode; // ��������
	private String phone; // ��ϵ�绰
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
