package edu.zjnu.graduation_statistics.service;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.dom4j.DocumentException;

import edu.zjnu.graduation_statistics.dao.TeachingPointDao;
import edu.zjnu.graduation_statistics.domain.TeachingPoint;
import edu.zjnu.graduation_statistics.utils.xmlUtil;

public class TeachingPointService {
	TeachingPointDao teachingPointDao = new TeachingPointDao();
	
	public List<TeachingPoint> selectAllTeachingPart(){
		List<TeachingPoint> teachingPointList = teachingPointDao.selectAllTeachingPart();
		try {
			loard(teachingPointList);
		} catch (DocumentException e) {
			System.out.println("我错了,"+e.getMessage());
		}
		return teachingPointList;
	}
	
	public List<TeachingPoint> selectTeachingPoints(String C_GradeId,String C_CollegeId,String T_Part){
		return teachingPointDao.selecTeachingPoint(C_GradeId, C_CollegeId, T_Part);
	}
	
	public void loard(List<TeachingPoint> teachingPointList) throws DocumentException {
		for (TeachingPoint teachingPoint : teachingPointList) {
			String t_Part = teachingPoint.getT_Part();
			String partName = xmlUtil.parseAtrById("TeachingPoint.xml", "part", t_Part);
			teachingPoint.setPartName(partName);
		}
	}

}
