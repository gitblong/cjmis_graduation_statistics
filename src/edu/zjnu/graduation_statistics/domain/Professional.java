package edu.zjnu.graduation_statistics.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professional implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1546156165L;
	private String P_Id;// 编号GUID
	private String P_cId;// 学院GUID
	private String P_Name;// 专业名称
	private String P_Type;// 专业类别
	private String typeName;//专业类别名称
	private String P_Level;// 专业层次
	private String levelName;//专业层次名称
	private String P_Structure;// 专业学制
	private String P_SchoolYear;// 入学年份
	private String P_ProfessionalType;// 专业类型
	private String P_Branches;// 学科门类。学位类别，(管理学....)
	private String P_Coding;// 专业编码
	private int P_LeastCredits;// 毕业需要的学分
	private List<Students> listStudents;
	public Professional() {
		listStudents = new ArrayList<Students>();
	}
	
	public Professional(String p_Id, String p_cId, String p_Name, String p_Type, String p_Level, String p_Structure,
			String p_SchoolYear, String p_ProfessionalType, String p_Branches, String p_Coding, int p_LeastCredits,
			List<Students> listStudents) {
		super();
		P_Id = p_Id;
		P_cId = p_cId;
		P_Name = p_Name;
		P_Type = p_Type;
		P_Level = p_Level;
		P_Structure = p_Structure;
		P_SchoolYear = p_SchoolYear;
		P_ProfessionalType = p_ProfessionalType;
		P_Branches = p_Branches;
		P_Coding = p_Coding;
		P_LeastCredits = p_LeastCredits;
		this.listStudents = listStudents;
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getP_Id() {
		return P_Id;
	}
	public void setP_Id(String p_Id) {
		P_Id = p_Id;
	}
	public String getP_cId() {
		return P_cId;
	}
	public void setP_cId(String p_cId) {
		P_cId = p_cId;
	}
	public String getP_Name() {
		return P_Name;
	}
	public void setP_Name(String p_Name) {
		P_Name = p_Name;
	}
	public String getP_Type() {
		return P_Type;
	}
	public void setP_Type(String p_Type) {
		P_Type = p_Type;
	}
	public String getP_Level() {
		return P_Level;
	}
	public void setP_Level(String p_Level) {
		P_Level = p_Level;
	}
	public String getP_Structure() {
		return P_Structure;
	}
	public void setP_Structure(String p_Structure) {
		P_Structure = p_Structure;
	}
	public String getP_SchoolYear() {
		return P_SchoolYear;
	}
	public void setP_SchoolYear(String p_SchoolYear) {
		P_SchoolYear = p_SchoolYear;
	}
	public String getP_ProfessionalType() {
		return P_ProfessionalType;
	}
	public void setP_ProfessionalType(String p_ProfessionalType) {
		P_ProfessionalType = p_ProfessionalType;
	}
	public String getP_Branches() {
		return P_Branches;
	}
	public void setP_Branches(String p_Branches) {
		P_Branches = p_Branches;
	}
	public String getP_Coding() {
		return P_Coding;
	}
	public void setP_Coding(String p_Coding) {
		P_Coding = p_Coding;
	}
	public int getP_LeastCredits() {
		return P_LeastCredits;
	}
	public void setP_LeastCredits(int p_LeastCredits) {
		P_LeastCredits = p_LeastCredits;
	}
	public List<Students> getListStudents() {
		return listStudents;
	}
	public void setListStudents(List<Students> listStudents) {
		this.listStudents = listStudents;
	}
	@Override
	public String toString() {
		return "Professional [P_Id=" + P_Id + ", P_cId=" + P_cId + ", P_Name=" + P_Name + ", P_Type=" + P_Type
				+ ", typeName=" + typeName + ", P_Level=" + P_Level + ", levelName=" + levelName + ", P_Structure="
				+ P_Structure + ", P_SchoolYear=" + P_SchoolYear + ", P_ProfessionalType=" + P_ProfessionalType
				+ ", P_Branches=" + P_Branches + ", P_Coding=" + P_Coding + ", P_LeastCredits=" + P_LeastCredits
				+ ", listStudents=" + listStudents + "]";
	}
	
	
}
