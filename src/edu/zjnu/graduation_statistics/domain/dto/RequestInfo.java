package edu.zjnu.graduation_statistics.domain.dto;

public class RequestInfo {
//	C_Grade, T_tId, T_Part, P_Coding, P_Level, P_Type, C_cId, classId, C_Name
	private String gradeId;//年级
	private String t_tId;//教学点
	private String collegeId;//学院Id GUID
	private String partId;//教学点区域
	private String pcoding;//专业编号
	private String pLevel;//层次
	private String pType;//类型
	private String classResearch;//班级搜索
	private String classesId;//班级Id GUID
	public RequestInfo() {
	}
	
	
	public RequestInfo(String gradeId, String t_tId, String collegeId, String partId, String pcoding, String pLevel,
			String pType, String classResearch, String classesId) {
		super();
		this.gradeId = gradeId;
		this.t_tId = t_tId;
		this.collegeId = collegeId;
		this.partId = partId;
		this.pcoding = pcoding;
		this.pLevel = pLevel;
		this.pType = pType;
		this.classResearch = classResearch;
		this.classesId = classesId;
	}


	public String getClassesId() {
		return classesId;
	}


	public void setClassesId(String classesId) {
		this.classesId = classesId;
	}


	public String getT_tId() {
		return t_tId;
	}


	public void setT_tId(String t_tId) {
		this.t_tId = t_tId;
	}


	public String getPcoding() {
		return pcoding;
	}


	public void setPcoding(String pcoding) {
		this.pcoding = pcoding;
	}


	public String getpLevel() {
		return pLevel;
	}


	public void setpLevel(String pLevel) {
		this.pLevel = pLevel;
	}


	public String getpType() {
		return pType;
	}


	public void setpType(String pType) {
		this.pType = pType;
	}


	public String getClassResearch() {
		return classResearch;
	}


	public void setClassResearch(String classResearch) {
		this.classResearch = classResearch;
	}


	public String getGradeId() {
		return gradeId;
	}


	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}


	public String getCollegeId() {
		return collegeId;
	}


	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}


	public String getPartId() {
		return partId;
	}


	public void setPartId(String partId) {
		this.partId = partId;
	}


	@Override
	public String toString() {
		return "RequestInfo [gradeId=" + gradeId + ", t_tId=" + t_tId + ", collegeId=" + collegeId + ", partId="
				+ partId + ", pcoding=" + pcoding + ", pLevel=" + pLevel + ", pType=" + pType + ", classResearch="
				+ classResearch + ", classesId=" + classesId + "]";
	}
	;
}
