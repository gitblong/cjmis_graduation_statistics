package edu.zjnu.graduation_statistics.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Students implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 12652656L;
	private String S_Id;// 编号GUID
	private int S_IsJXS;// 是否进修生
	private String S_Name;// 姓名
	private String S_Sex;// 性别
	private String S_Birthday;// 出生日期
	private String S_NativePlace_Province;// 籍贯-省 
	private String S_NativePlace_City;// 籍贯-市
	private String S_PoliticalFeature;// 政治面貌
	private String S_Race;// 民族
	private String S_IDCard;// 身份证号码
	private String S_Year;// 入学年份
	private String S_Address;// 家庭地址
	private String S_Zip;// 邮编
	private String S_Phone;// 电话、
	private String S_ClassesId;// 班号 GUID
	private String S_StudyClassesId;// 上课班号
	private String S_ExamineeNum;// 考生号
	private String S_ResearchNum;// 准考证号
	private String S_TotalScore;// 总分
	private String S_StuNum;// 学号
	private String S_TotalCredits;//总学分
	private String S_Changes;//异动
	
	private String S_JstuNum;//进修学号
	private String S_JClassNum;//进修班号
	private String S_JClassName;//进修班名
	
	private Classes classes;//班级
	private List<CourseScheduling> listCourseScheduling;
	private Professional professional;
	private List<String> listCourseName;
	private String teachingName;
	private String CollegeName;
	
	public Students() {
		listCourseScheduling = new ArrayList<CourseScheduling>();
		listCourseName = new ArrayList<String>();
	}

	public String getS_Id() {
		return S_Id;
	}

	public void setS_Id(String s_Id) {
		S_Id = s_Id;
	}

	public int getS_IsJXS() {
		return S_IsJXS;
	}

	public void setS_IsJXS(int s_IsJXS) {
		S_IsJXS = s_IsJXS;
	}

	public String getS_Name() {
		return S_Name;
	}

	public void setS_Name(String s_Name) {
		S_Name = s_Name;
	}

	public String getS_Sex() {
		return S_Sex;
	}

	public void setS_Sex(String s_Sex) {
		S_Sex = s_Sex;
	}

	public String getS_Birthday() {
		return S_Birthday;
	}

	public void setS_Birthday(String s_Birthday) {
		S_Birthday = s_Birthday;
	}

	public String getS_NativePlace_Province() {
		return S_NativePlace_Province;
	}

	public void setS_NativePlace_Province(String s_NativePlace_Province) {
		S_NativePlace_Province = s_NativePlace_Province;
	}

	public String getS_NativePlace_City() {
		return S_NativePlace_City;
	}

	public void setS_NativePlace_City(String s_NativePlace_City) {
		S_NativePlace_City = s_NativePlace_City;
	}

	public String getS_PoliticalFeature() {
		return S_PoliticalFeature;
	}

	public void setS_PoliticalFeature(String s_PoliticalFeature) {
		S_PoliticalFeature = s_PoliticalFeature;
	}

	public String getS_Race() {
		return S_Race;
	}

	public void setS_Race(String s_Race) {
		S_Race = s_Race;
	}

	public String getS_IDCard() {
		return S_IDCard;
	}

	public void setS_IDCard(String s_IDCard) {
		S_IDCard = s_IDCard;
	}

	public String getS_Year() {
		return S_Year;
	}

	public void setS_Year(String s_Year) {
		S_Year = s_Year;
	}

	public String getS_Address() {
		return S_Address;
	}

	public void setS_Address(String s_Address) {
		S_Address = s_Address;
	}

	public String getS_Zip() {
		return S_Zip;
	}

	public void setS_Zip(String s_Zip) {
		S_Zip = s_Zip;
	}

	public String getS_Phone() {
		return S_Phone;
	}

	public void setS_Phone(String s_Phone) {
		S_Phone = s_Phone;
	}

	public String getS_ClassesId() {
		return S_ClassesId;
	}

	public void setS_ClassesId(String s_ClassesId) {
		S_ClassesId = s_ClassesId;
	}

	public String getS_StudyClassesId() {
		return S_StudyClassesId;
	}

	public void setS_StudyClassesId(String s_StudyClassesId) {
		S_StudyClassesId = s_StudyClassesId;
	}

	public String getS_ExamineeNum() {
		return S_ExamineeNum;
	}

	public void setS_ExamineeNum(String s_ExamineeNum) {
		S_ExamineeNum = s_ExamineeNum;
	}

	public String getS_ResearchNum() {
		return S_ResearchNum;
	}

	public void setS_ResearchNum(String s_ResearchNum) {
		S_ResearchNum = s_ResearchNum;
	}

	public String getS_TotalScore() {
		return S_TotalScore;
	}

	public void setS_TotalScore(String s_TotalScore) {
		S_TotalScore = s_TotalScore;
	}

	public String getS_StuNum() {
		return S_StuNum;
	}

	public void setS_StuNum(String s_StuNum) {
		S_StuNum = s_StuNum;
	}

	public String getS_TotalCredits() {
		return S_TotalCredits;
	}

	public void setS_TotalCredits(String s_TotalCredits) {
		S_TotalCredits = s_TotalCredits;
	}

	public String getS_Changes() {
		return S_Changes;
	}

	public void setS_Changes(String s_Changes) {
		S_Changes = s_Changes;
	}

	public String getS_JstuNum() {
		return S_JstuNum;
	}

	public void setS_JstuNum(String s_JstuNum) {
		S_JstuNum = s_JstuNum;
	}

	public String getS_JClassNum() {
		return S_JClassNum;
	}

	public void setS_JClassNum(String s_JClassNum) {
		S_JClassNum = s_JClassNum;
	}

	public String getS_JClassName() {
		return S_JClassName;
	}

	public void setS_JClassName(String s_JClassName) {
		S_JClassName = s_JClassName;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public List<CourseScheduling> getListCourseScheduling() {
		return listCourseScheduling;
	}

	public void setListCourseScheduling(List<CourseScheduling> listCourseScheduling) {
		this.listCourseScheduling = listCourseScheduling;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public List<String> getListCourseName() {
		return listCourseName;
	}

	public void setListCourseName(List<String> listCourseName) {
		this.listCourseName = listCourseName;
	}

	public String getTeachingName() {
		return teachingName;
	}

	public void setTeachingName(String teachingName) {
		this.teachingName = teachingName;
	}

	public String getCollegeName() {
		return CollegeName;
	}

	public void setCollegeName(String collegeName) {
		CollegeName = collegeName;
	}

	@Override
	public String toString() {
		return "Students [S_Id=" + S_Id + ", S_IsJXS=" + S_IsJXS + ", S_Name=" + S_Name + ", S_Sex=" + S_Sex
				+ ", S_Birthday=" + S_Birthday + ", S_NativePlace_Province=" + S_NativePlace_Province
				+ ", S_NativePlace_City=" + S_NativePlace_City + ", S_PoliticalFeature=" + S_PoliticalFeature
				+ ", S_Race=" + S_Race + ", S_IDCard=" + S_IDCard + ", S_Year=" + S_Year + ", S_Address=" + S_Address
				+ ", S_Zip=" + S_Zip + ", S_Phone=" + S_Phone + ", S_ClassesId=" + S_ClassesId + ", S_StudyClassesId="
				+ S_StudyClassesId + ", S_ExamineeNum=" + S_ExamineeNum + ", S_ResearchNum=" + S_ResearchNum
				+ ", S_TotalScore=" + S_TotalScore + ", S_StuNum=" + S_StuNum + ", S_TotalCredits=" + S_TotalCredits
				+ ", S_Changes=" + S_Changes + ", S_JstuNum=" + S_JstuNum + ", S_JClassNum=" + S_JClassNum
				+ ", S_JClassName=" + S_JClassName + ", teachingName=" + teachingName + ", CollegeName=" + CollegeName
				+ "]";
	}
	
}
