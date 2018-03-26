package edu.zjnu.graduation_statistics.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Classes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 137656445L;
	private String C_Id;// 编号GUID
	private String C_cId;// 班级编号
	private String C_Name;// 班级名称
	private String C_Grade;// 年级
	private String C_pId;// 专业编号
	private String C_tID;// 教学点编号
	private String C_Structure;// 专业学制
	private String C_AdmissionTime;//开学日期
	private List<Students> listStudents;
	public Classes() {
		listStudents = new ArrayList<Students>();
	}
	public Classes(String c_Id, String c_cId, String c_Name, String c_Grade, String c_pId, String c_tID,
			String c_Structure, List<Students> listStudents) {
		super();
		C_Id = c_Id;
		C_cId = c_cId;
		C_Name = c_Name;
		C_Grade = c_Grade;
		C_pId = c_pId;
		C_tID = c_tID;
		C_Structure = c_Structure;
		this.listStudents = listStudents;
	}
	
	
	public String getC_AdmissionTime() {
		return C_AdmissionTime;
	}
	public void setC_AdmissionTime(String c_AdmissionTime) {
		C_AdmissionTime = c_AdmissionTime;
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
	public String getC_Grade() {
		return C_Grade;
	}
	public void setC_Grade(String c_Grade) {
		C_Grade = c_Grade;
	}
	public String getC_pId() {
		return C_pId;
	}
	public void setC_pId(String c_pId) {
		C_pId = c_pId;
	}
	public String getC_tID() {
		return C_tID;
	}
	public void setC_tID(String c_tID) {
		C_tID = c_tID;
	}
	public String getC_Structure() {
		return C_Structure;
	}
	public void setC_Structure(String c_Structure) {
		C_Structure = c_Structure;
	}
	public List<Students> getListStudents() {
		return listStudents;
	}
	public void setListStudents(List<Students> listStudents) {
		this.listStudents = listStudents;
	}
	@Override
	public String toString() {
		return "Classes [C_Id=" + C_Id + ", C_cId=" + C_cId + ", C_Name=" + C_Name + ", C_Grade=" + C_Grade + ", C_pId="
				+ C_pId + ", C_tID=" + C_tID + ", C_Structure=" + C_Structure + ", C_AdmissionTime=" + C_AdmissionTime
				+ ", listStudents=" + listStudents + "]";
	}
	
}
