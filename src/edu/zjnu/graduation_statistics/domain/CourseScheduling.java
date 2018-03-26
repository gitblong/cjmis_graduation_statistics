package edu.zjnu.graduation_statistics.domain;

import java.io.Serializable;

public class CourseScheduling implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1789489L;
	private String C_Id;
	private String C_cId;// 课程GUID
	private String C_cName;// 课程名称
	private String C_tId;// 教学点GUID
	private String C_TechId;
	private String C_PId;// 开课专业
	private String C_aId;
	private String C_ClassId;// 上课班级编号
	private String c_Credits;// 学分

	public String getC_Credits() {
		return c_Credits;
	}

	public void setC_Credits(String c_Credits) {
		this.c_Credits = c_Credits;
	}

	public CourseScheduling() {
		// TODO Auto-generated constructor stub
	}

	public CourseScheduling(String c_Id, String c_cId, String c_cName, String c_tId, String c_TechId, String c_PId,
			String c_aId, String c_ClassId) {
		super();
		C_Id = c_Id;
		C_cId = c_cId;
		C_cName = c_cName;
		C_tId = c_tId;
		C_TechId = c_TechId;
		C_PId = c_PId;
		C_aId = c_aId;
		C_ClassId = c_ClassId;
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

	public String getC_cName() {
		return C_cName;
	}

	public void setC_cName(String c_cName) {
		C_cName = c_cName;
	}

	public String getC_tId() {
		return C_tId;
	}

	public void setC_tId(String c_tId) {
		C_tId = c_tId;
	}

	public String getC_TechId() {
		return C_TechId;
	}

	public void setC_TechId(String c_TechId) {
		C_TechId = c_TechId;
	}

	public String getC_PId() {
		return C_PId;
	}

	public void setC_PId(String c_PId) {
		C_PId = c_PId;
	}

	public String getC_aId() {
		return C_aId;
	}

	public void setC_aId(String c_aId) {
		C_aId = c_aId;
	}

	public String getC_ClassId() {
		return C_ClassId;
	}

	public void setC_ClassId(String c_ClassId) {
		C_ClassId = c_ClassId;
	}

	@Override
	public String toString() {
		return "CourseScheduling [C_Id=" + C_Id + ", C_cId=" + C_cId + ", C_cName=" + C_cName + ", C_tId=" + C_tId
				+ ", C_TechId=" + C_TechId + ", C_PId=" + C_PId + ", C_aId=" + C_aId + ", C_ClassId=" + C_ClassId
				+ ", c_Credits=" + c_Credits + "]";
	}

}
