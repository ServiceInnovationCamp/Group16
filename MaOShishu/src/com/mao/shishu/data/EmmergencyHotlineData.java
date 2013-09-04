package com.mao.shishu.data;

public class EmmergencyHotlineData {
	private String title;
	private String mobile;

	public EmmergencyHotlineData(String title, String mobile) {
		this.title = title;
		this.mobile = mobile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
