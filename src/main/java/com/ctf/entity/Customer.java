package com.ctf.entity;

/**
 * @author Administrator
 *
 */
public class Customer {

	private Integer id;
	private String khno; 
	private String name; 
	private String address; 
	private String postCode;
	private String phone;
	private Float fund;
	private Float financing;
	private Integer companyID;
	private String barcode;
	private String qrcode;
	private String gmt_create;
	private String gmt_modified;
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
	public Float getFund() {
		return fund;
	}
	public void setFund(Float fund) {
		this.fund = fund;
	}
	public Float getFinancing() {
		return financing;
	}
	public void setFinancing(Float financing) {
		this.financing = financing;
	}
	public Integer getCompanyID() {
		return companyID;
	}
	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public String getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}
	public String getGmt_modified() {
		return gmt_modified;
	}
	public void setGmt_modified(String gmt_modified) {
		this.gmt_modified = gmt_modified;
	}
	
	
}
