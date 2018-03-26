package edu.zjnu.graduation_statistics.service;

import java.util.List;

import edu.zjnu.graduation_statistics.dao.CollegeDao;
import edu.zjnu.graduation_statistics.domain.College;

public class CollegeService {

	CollegeDao collegeDao = new CollegeDao();
	
	public List<College>selectAllCollege(){
		
		return collegeDao.selectAllCollege();
	}
	
}
