package edu.zjnu.graduation_statistics.domain;

import java.io.Serializable;

public class Courses implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1265656858L;
	private String C_Id;// 编号GUID
	private String C_cId;// 课程编号
	private String C_ccId;// 所属学院编号
	private String C_Name;// 所属学院名称
	private int C_Credits;// 学分
	private College college;
	private Elective elective;
	public Courses() {
	}
	public Courses(String c_Id, String c_cId, String c_ccId, String c_Name, int c_Credits, College college,
			Elective elective) {
		super();
		C_Id = c_Id;
		C_cId = c_cId;
		C_ccId = c_ccId;
		C_Name = c_Name;
		C_Credits = c_Credits;
		this.college = college;
		this.elective = elective;
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
	public String getC_ccId() {
		return C_ccId;
	}
	public void setC_ccId(String c_ccId) {
		C_ccId = c_ccId;
	}
	public String getC_Name() {
		return C_Name;
	}
	public void setC_Name(String c_Name) {
		C_Name = c_Name;
	}
	public int getC_Credits() {
		return C_Credits;
	}
	public void setC_Credits(int c_Credits) {
		C_Credits = c_Credits;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public Elective getElective() {
		return elective;
	}
	public void setElective(Elective elective) {
		this.elective = elective;
	}
	@Override
	public String toString() {
		return "Courses [C_Id=" + C_Id + ", C_cId=" + C_cId + ", C_ccId=" + C_ccId + ", C_Name=" + C_Name
				+ ", C_Credits=" + C_Credits + ", college=" + college + ", elective=" + elective + "]";
	}
	
}
