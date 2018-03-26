package edu.zjnu.graduation_statistics.domain.dto;

public class ResultInfo {

	private boolean success;
	private String error;
	private String info;
	public ResultInfo() {
	}
	
	public ResultInfo(boolean success, String error, String info) {
		super();
		this.success = success;
		this.error = error;
		this.info = info;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "ResultInfo [success=" + success + ", error=" + error + ", info=" + info + "]";
	}
	
	
}
