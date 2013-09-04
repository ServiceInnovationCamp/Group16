package com.mao.shishu.data;

public class AdvisorData {
	private String title;
	private String hospital;
	private String mobile;

	public AdvisorData(String title, String hospital, String mobile) {
		this.title = title;
		this.hospital = hospital;
		this.mobile = mobile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
