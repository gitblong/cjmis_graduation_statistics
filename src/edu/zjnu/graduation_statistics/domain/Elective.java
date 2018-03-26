package edu.zjnu.graduation_statistics.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Elective implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 15264548L;
	private String E_Id;// 编号GUID
	private String E_csId;// 排课的课程GUID
	private String E_tId;// 对应的教学计划课程GUID
	private String E_sId;// 学生GUID
	private String E_Score1;//正考
	private String E_Score2;//缓考
	private String E_Score3;//补考
	private String E_Score4;//重修
	private String E_Score5;//再考
	private String E_Score6;
	private String E_Score7;
	private List<Students> listStudents;
	private Courses course;
	private List<TeachingPoint> listTeachingPoint;
	public Elective() {
		listStudents = new ArrayList<Students>();
		listTeachingPoint = new ArrayList<TeachingPoint>();
	}
	public Elective(String e_Id, String e_csId, String e_tId, String e_sId, String e_Score1, String e_Score2,
			String e_Score3, String e_Score4, String e_Score5, String e_Score6, String e_Score7,
			List<Students> listStudents, Courses course, List<TeachingPoint> listTeachingPoint) {
		super();
		E_Id = e_Id;
		E_csId = e_csId;
		E_tId = e_tId;
		E_sId = e_sId;
		E_Score1 = e_Score1;
		E_Score2 = e_Score2;
		E_Score3 = e_Score3;
		E_Score4 = e_Score4;
		E_Score5 = e_Score5;
		E_Score6 = e_Score6;
		E_Score7 = e_Score7;
		this.listStudents = listStudents;
		this.course = course;
		this.listTeachingPoint = listTeachingPoint;
	}
	
	
	public String getE_Id() {
		return E_Id;
	}
	public void setE_Id(String e_Id) {
		E_Id = e_Id;
	}
	public String getE_csId() {
		return E_csId;
	}
	public void setE_csId(String e_csId) {
		E_csId = e_csId;
	}
	public String getE_tId() {
		return E_tId;
	}
	public void setE_tId(String e_tId) {
		E_tId = e_tId;
	}
	public String getE_sId() {
		return E_sId;
	}
	public void setE_sId(String e_sId) {
		E_sId = e_sId;
	}
	public String getE_Score1() {
		return E_Score1;
	}
	public void setE_Score1(String e_Score1) {
		E_Score1 = e_Score1;
	}
	public String getE_Score2() {
		return E_Score2;
	}
	public void setE_Score2(String e_Score2) {
		E_Score2 = e_Score2;
	}
	public String getE_Score3() {
		return E_Score3;
	}
	public void setE_Score3(String e_Score3) {
		E_Score3 = e_Score3;
	}
	public String getE_Score4() {
		return E_Score4;
	}
	public void setE_Score4(String e_Score4) {
		E_Score4 = e_Score4;
	}
	public String getE_Score5() {
		return E_Score5;
	}
	public void setE_Score5(String e_Score5) {
		E_Score5 = e_Score5;
	}
	public String getE_Score6() {
		return E_Score6;
	}
	public void setE_Score6(String e_Score6) {
		E_Score6 = e_Score6;
	}
	public String getE_Score7() {
		return E_Score7;
	}
	public void setE_Score7(String e_Score7) {
		E_Score7 = e_Score7;
	}
	public List<Students> getListStudents() {
		return listStudents;
	}
	public void setListStudents(List<Students> listStudents) {
		this.listStudents = listStudents;
	}
	public Courses getCourse() {
		return course;
	}
	public void setCourse(Courses course) {
		this.course = course;
	}
	public List<TeachingPoint> getListTeachingPoint() {
		return listTeachingPoint;
	}
	public void setListTeachingPoint(List<TeachingPoint> listTeachingPoint) {
		this.listTeachingPoint = listTeachingPoint;
	}
	@Override
	public String toString() {
		return "Elective [E_Id=" + E_Id + ", E_csId=" + E_csId + ", E_tId=" + E_tId + ", E_sId=" + E_sId + ", E_Score1="
				+ E_Score1 + ", E_Score2=" + E_Score2 + ", E_Score3=" + E_Score3 + ", E_Score4=" + E_Score4
				+ ", E_Score5=" + E_Score5 + ", E_Score6=" + E_Score6 + ", E_Score7=" + E_Score7 + ", listStudents="
				+ listStudents + ", course=" + course + ", listTeachingPoint=" + listTeachingPoint + "]";
	}
	
}
