package com.ctf.entity;

public class Promote {
	private int id;
	private String promoteID;
	private String userOpenID;
	
	public Promote() {
		super();
	}
	public Promote(String promoteID, String userOpenID) {
		this.promoteID = promoteID;
		this.userOpenID = userOpenID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPromoteID() {
		return promoteID;
	}
	public void setPromoteID(String promoteID) {
		this.promoteID = promoteID;
	}
	public String getUserOpenID() {
		return userOpenID;
	}
	public void setUserOpenID(String userOpenID) {
		this.userOpenID = userOpenID;
	}
	
}
