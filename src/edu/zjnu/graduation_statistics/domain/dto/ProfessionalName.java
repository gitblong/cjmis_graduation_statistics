package edu.zjnu.graduation_statistics.domain.dto;

public class ProfessionalName {

	private String grade;//年级
	private String collegeId;//学院
	private String t_Part;//教学区域
	private String t_tId;//教学点
	private String p_Type;//专业类别
	private String p_Level;//专业层次
	public ProfessionalName() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProfessionalName [grade=" + grade + ", collegeId=" + collegeId + ", t_Part=" + t_Part + ", t_tId="
				+ t_tId + ", p_Type=" + p_Type + ", p_Level=" + p_Level + "]";
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	public String getT_Part() {
		return t_Part;
	}
	public void setT_Part(String t_Part) {
		this.t_Part = t_Part;
	}
	public String getT_tId() {
		return t_tId;
	}
	public void setT_tId(String t_tId) {
		this.t_tId = t_tId;
	}
	public String getP_Type() {
		return p_Type;
	}
	public void setP_Type(String p_Type) {
		this.p_Type = p_Type;
	}
	public String getP_Level() {
		return p_Level;
	}
	public void setP_Level(String p_Level) {
		this.p_Level = p_Level;
	}
	
}
