package edu.zjnu.graduation_statistics.domain.dto;

public class FromName {
	private String college;
	private String teachingPart;
	private String teachingPoint;
	private String grade;
	private String professionalType;
	private String professionalLevel;
	private String professionalName;
	private String searchClasses;
	private String classes;

	public FromName() {
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getTeachingPart() {
		return teachingPart;
	}

	public void setTeachingPart(String teachingPart) {
		this.teachingPart = teachingPart;
	}

	public String getTeachingPoint() {
		return teachingPoint;
	}

	public void setTeachingPoint(String teachingPoint) {
		this.teachingPoint = teachingPoint;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getProfessionalType() {
		return professionalType;
	}

	public void setProfessionalType(String professionalType) {
		this.professionalType = professionalType;
	}

	public String getProfessionalLevel() {
		return professionalLevel;
	}

	public void setProfessionalLevel(String professionalLevel) {
		this.professionalLevel = professionalLevel;
	}

	public String getProfessionalName() {
		return professionalName;
	}

	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
	}

	public String getSearchClasses() {
		return searchClasses;
	}

	public void setSearchClasses(String searchClasses) {
		this.searchClasses = searchClasses;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "FromName [college=" + college + ", teachingPart=" + teachingPart + ", teachingPoint=" + teachingPoint
				+ ", grade=" + grade + ", professionalType=" + professionalType + ", professionalLevel="
				+ professionalLevel + ", professionalName=" + professionalName + ", searchClasses=" + searchClasses
				+ ", classes=" + classes + "]";
	}
	
}
