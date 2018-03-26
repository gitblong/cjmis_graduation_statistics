package edu.zjnu.graduation_statistics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.map.HashedMap;
import org.dom4j.DocumentException;

import edu.zjnu.graduation_statistics.dao.ProfessionalDao;
import edu.zjnu.graduation_statistics.domain.Professional;
import edu.zjnu.graduation_statistics.domain.dto.ProfessionalName;
import edu.zjnu.graduation_statistics.utils.xmlUtil;
import javassist.expr.NewArray;

public class ProfessionalService {

	ProfessionalDao professionalDao = new ProfessionalDao();
//	grade collegeId t_PartId t_tId p_Type p_Level 
	public List<Professional> selectAllProfessional(ProfessionalName professionalName){
		
		String grade = professionalName.getGrade();
		String collegeId= professionalName.getCollegeId();
		String t_Part=professionalName.getT_Part();
		String t_tId=professionalName.getT_tId();
		String p_Type=professionalName.getP_Type();
		String p_Level= professionalName.getP_Level();
		List<Professional> selectAllProfessional = professionalDao.selectAllProfessional(grade,collegeId,t_Part,t_tId,p_Type,p_Level);
		List<Professional> professionaLists = loardProfessionalLists(selectAllProfessional);
		
		return professionaLists;
	}
	
	public List<Professional> loardProfessionalLists(List<Professional> selectAllProfessional) {
		Map<String, Professional> map = new HashedMap();
		for (Professional professional : selectAllProfessional) {
			map.put(professional.getP_Coding(), professional);
		}
		List<Professional> professionaLists = new ArrayList<Professional>();
		List<Map.Entry<String, Professional>> maplist = new ArrayList<Map.Entry<String,Professional>>(map.entrySet());
		Collections.sort(maplist,new Comparator<Map.Entry<String, Professional>>() {

			@Override
			public int compare(Entry<String, Professional> o1, Entry<String, Professional> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		for (Entry<String, Professional> entry : maplist) {
			professionaLists.add(entry.getValue());
		}
		return professionaLists;
	}

	public List<Professional> selectAllProfessionalType(){
		List<Professional> selectAllProfessionalType = new ArrayList<Professional>();
		try {
			selectAllProfessionalType = xmlUtil.parseAtrById("Professional.xml", "type");
		} catch (DocumentException e1) {
			System.out.println("我错了"+e1.getMessage());
		}
		return selectAllProfessionalType;
	}
	
	public List<Professional> selectAllProfessionalLevel(){
		List<Professional> selectAllProfessionalType = new ArrayList<Professional>();
		try {
			selectAllProfessionalType = xmlUtil.parseAtrById("Professional.xml", "level");
		} catch (DocumentException e1) {
			System.out.println("我错了"+e1.getMessage());
		}
		return selectAllProfessionalType;
	}

	public void loard(List<Professional> professionalLists) throws DocumentException {
		for (Professional professional : professionalLists) {
			String p_Level = professional.getP_Level();
			String p_Type = professional.getP_Type();
			if (!p_Level.isEmpty()&&p_Level !=null) {
				professional.setLevelName(xmlUtil.parseAtrById("Professional.xml", "level", p_Level));
			}
			if (!p_Type.isEmpty()&&p_Type !=null) {
				professional.setTypeName(xmlUtil.parseAtrById("Professional.xml", "type", p_Type));
			}
		}
	}
}
