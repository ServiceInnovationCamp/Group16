package com.mao.shishu.data;

public class EmmergencyHostpitalData {
	private String hospitalName;
	private String location;
	private String open;
	private String time;
	private String mobileNumber;

	public EmmergencyHostpitalData(String hospitalName, String location,
			String open, String time, String mobileNumber) {
		this.hospitalName = hospitalName;
		this.location = location;
		this.open = open;
		this.time = time;
		this.mobileNumber = mobileNumber;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
