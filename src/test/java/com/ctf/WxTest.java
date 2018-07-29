package com.ctf;

import org.junit.Test;

import com.ctf.controller.WxController;

public class WxTest {
	
	@Test
	public void menuTest(){
		WxController wx=new WxController();
		wx.createMenu(null);
	}
}
