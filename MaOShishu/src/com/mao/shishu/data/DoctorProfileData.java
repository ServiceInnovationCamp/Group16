package com.mao.shishu.data;

public class DoctorProfileData {
	private String title;
	private String profile;
	private String mobile;
	private String hospital;

	public DoctorProfileData(String title, String profile, String mobile,
			String hospital) {
		this.title = title;
		this.profile = profile;
		this.mobile = mobile;
		this.hospital = hospital;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
