package edu.zjnu.graduation_statistics.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class College implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 154651561L;
	private String C_Id;// 编号GUID
	private String C_cId;// 学院编号
	private String C_Name;// 学院名称
	private String C_Remark;
	private List<Courses> courseLists;
	public College() {
		courseLists = new ArrayList<Courses>();
	}
	public College(String c_Id, String c_cId, String c_Name, String c_Remark, List<Courses> courseLists) {
		super();
		C_Id = c_Id;
		C_cId = c_cId;
		C_Name = c_Name;
		C_Remark = c_Remark;
		this.courseLists = courseLists;
	}
	public String getC_Id() {
		return C_Id;
	}
	public void setC_Id(String c_Id) {
		C_Id = c_Id;
	}
	public String getC_cId() {
		return C_cId;
	}
	public void setC_cId(String c_cId) {
		C_cId = c_cId;
	}
	public String getC_Name() {
		return C_Name;
	}
	public void setC_Name(String c_Name) {
		C_Name = c_Name;
	}
	public String getC_Remark() {
		return C_Remark;
	}
	public void setC_Remark(String c_Remark) {
		C_Remark = c_Remark;
	}
	public List<Courses> getCourseLists() {
		return courseLists;
	}
	public void setCourseLists(List<Courses> courseLists) {
		this.courseLists = courseLists;
	}
	@Override
	public String toString() {
		return "College [C_Id=" + C_Id + ", C_cId=" + C_cId + ", C_Name=" + C_Name + ", C_Remark=" + C_Remark
				+ ", courseLists=" + courseLists + "]";
	}
	
}
