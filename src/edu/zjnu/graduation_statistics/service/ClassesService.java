package edu.zjnu.graduation_statistics.service;

import java.util.List;

import edu.zjnu.graduation_statistics.dao.ClassesDao;
import edu.zjnu.graduation_statistics.domain.Classes;
import edu.zjnu.graduation_statistics.domain.dto.RequestInfo;

public class ClassesService {

	ClassesDao classesDao = new ClassesDao();
	
	public List<Classes> selectAllClasses(RequestInfo requestInfo){
		String gradeId = requestInfo.getGradeId();
		String t_tId = requestInfo.getT_tId();
		String collegeId = requestInfo.getCollegeId();
		String partId = requestInfo.getPartId();
		String pcoding = requestInfo.getPcoding();
		String getpLevel = requestInfo.getpLevel();
		String getpType = requestInfo.getpType();
		String classResearch = requestInfo.getClassResearch();
		String classId = classResearch.replaceAll("\\D+", "");
		String className = classResearch.replaceAll("\\d+","");
		return classesDao.selectAllClasses(gradeId, t_tId, partId, pcoding, getpLevel, getpType, collegeId, classId, classResearch);
	}
	
	public List<Classes> getAllGrade(){
		return classesDao.getAllGrade();
	}
	
}
