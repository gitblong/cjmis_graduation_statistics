package edu.zjnu.graduation_statistics.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeachingPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String T_Id;/// 编号GUID
	private String T_tId;// 教学点编号
	private String T_Name;// 教学点名称
	private String T_Part;//教学点区域
	private String partName;//教学点名称
	private List<Elective> listElectives;
	
	public TeachingPoint() {
		listElectives = new ArrayList<Elective>();
	}

	public TeachingPoint(String t_Id, String t_tId, String t_Name, List<Elective> listElectives) {
		super();
		T_Id = t_Id;
		T_tId = t_tId;
		T_Name = t_Name;
		this.listElectives = listElectives;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getT_Part() {
		return T_Part;
	}

	public void setT_Part(String t_Part) {
		T_Part = t_Part;
	}

	public String getT_Id() {
		return T_Id;
	}

	public void setT_Id(String t_Id) {
		T_Id = t_Id;
	}

	public String getT_tId() {
		return T_tId;
	}

	public void setT_tId(String t_tId) {
		T_tId = t_tId;
	}

	public String getT_Name() {
		return T_Name;
	}

	public void setT_Name(String t_Name) {
		T_Name = t_Name;
	}

	public List<Elective> getListElectives() {
		return listElectives;
	}

	public void setListElectives(List<Elective> listElectives) {
		this.listElectives = listElectives;
	}

	@Override
	public String toString() {
		return "TeachingPoint [T_Id=" + T_Id + ", T_tId=" + T_tId + ", T_Name=" + T_Name + ", T_Part=" + T_Part
				+ ", partName=" + partName + ", listElectives=" + listElectives + "]";
	}
	
	
	
}
