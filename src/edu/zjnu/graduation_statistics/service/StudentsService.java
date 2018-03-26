package edu.zjnu.graduation_statistics.service;

import java.util.ArrayList;
import java.util.List;

import edu.zjnu.graduation_statistics.dao.ClassesDao;
import edu.zjnu.graduation_statistics.dao.CollegeDao;
import edu.zjnu.graduation_statistics.dao.CourseSchedulingDao;
import edu.zjnu.graduation_statistics.dao.ElectiveDao;
import edu.zjnu.graduation_statistics.dao.ProfessionalDao;
import edu.zjnu.graduation_statistics.dao.StudentsDao;
import edu.zjnu.graduation_statistics.dao.TeachingPointDao;
import edu.zjnu.graduation_statistics.domain.Classes;
import edu.zjnu.graduation_statistics.domain.CourseScheduling;
import edu.zjnu.graduation_statistics.domain.Elective;
import edu.zjnu.graduation_statistics.domain.Professional;
import edu.zjnu.graduation_statistics.domain.Students;
import edu.zjnu.graduation_statistics.domain.dto.FromName;
import edu.zjnu.graduation_statistics.domain.dto.RequestInfo;

public class StudentsService {
	private boolean addUnPassStudents= true;
	public StudentsService() {
		unPasStudentList = new ArrayList<Students>();
	}
	StudentsDao studentsDao = new StudentsDao();
	ElectiveDao electiveDao = new ElectiveDao();
	ClassesDao classesDao = new ClassesDao();
	ElectiveService electiveService = new ElectiveService();
	CourseSchedulingDao courseSchedulingDao = new CourseSchedulingDao();
	ProfessionalDao professionalDao = new ProfessionalDao();
	TeachingPointDao teachingPointDao = new TeachingPointDao();
	CollegeDao collegeDao = new CollegeDao();
	public List<Students> selectStudentsByClassesId(RequestInfo requestInfo){
		
		String classesId = requestInfo.getClassesId();
		String classResearch = requestInfo.getClassResearch();
		String classId = classResearch.replaceAll("\\D", "");
		String className = classResearch.replaceAll("\\d", "");
		String collegeId = requestInfo.getCollegeId();
		String gradeId = requestInfo.getGradeId();
		String partId = requestInfo.getPartId();
		String pcoding = requestInfo.getPcoding();
		String getpLevel = requestInfo.getpLevel();
		String getpType = requestInfo.getpType();
		String t_tId = requestInfo.getT_tId();
		
		return studentsDao.selectStudentByClassesId(gradeId, t_tId, partId, pcoding, getpLevel, getpType, collegeId, classId, className, classesId);
	}
	public List<Students> unPasStudentList;
	public List<Students> selectStudentByExcelStudentNum(){
		List<String> studentNumbers = new ArrayList<String>();
		studentNumbers.add("'106111010021'");
		studentNumbers.add("'151107040024'");
		
		return null/*studentsDao.selectStudentByStuNum(studentNumbers)*/;
	}
	/**
	 * 根据已给的学号，向数据库查询
	 * @param studentNumbers
	 * @return
	 */
	public List<Students> selectUnPassStudentsExcelNum(String studentNumbers){
		List<Students> getAllStudents = studentsDao.selectStudentByStuNum(studentNumbers);
		selectExcetice(getAllStudents);
		return unPasStudentList;
	}
	/**
	 * 
	 * 通过下拉列表查询出所有学生
	 * @param form
	 * @return
	 */
	public List<Students>  selectUnPassStudentsInfo(FromName form){
		System.out.println("");
		String classesId = form.getClasses();
		String classResearch = form.getSearchClasses();
		String classId = classResearch.replaceAll("\\D", "");
		String className = classResearch.replaceAll("\\d", "");
		String collegeId = form.getCollege();
		String gradeId = form.getGrade();
		String partId = form.getTeachingPart();
		String pcoding = form.getProfessionalName();
		String getpLevel = form.getProfessionalType();
		String getpType = form.getProfessionalType();
		String t_tId = form.getTeachingPoint();
		List<Students> selectStudentByClassesId = studentsDao.selectStudentByClassesId(gradeId, t_tId, partId, pcoding, getpLevel, getpType, collegeId, classId, className, classesId);
		selectExcetice(selectStudentByClassesId);
		return unPasStudentList;
	}

	public void selectExcetice(List<Students> selectStudentByClassesId) {
		for (Students student : selectStudentByClassesId) {
			String grade = classesDao.selectGradeBySId(student.getS_Id());
			List<Elective> unPassElective = electiveService.selectUnPassElectiveForStudent(student,grade);
			if (!unPassElective.isEmpty()) {
				selectUnPassElective(student,unPassElective,grade);
				addDetailInfo(student, grade);
				unPasStudentList.add(student);
				addUnPassStudents = false;
			}
			if (unPassCreditsToStudents(student,grade)&&addUnPassStudents) {
				addDetailInfo(student,grade);
				unPasStudentList.add(student);
			}
			addUnPassStudents = true;
			
		}
	}

	public boolean unPassCreditsToStudents(Students student,String grade){
		String s_Id = student.getS_Id();
		int gradeInt = Integer.parseInt(grade);
		if(gradeInt>=2008){
			Professional professional = professionalDao.selectProfessionalBySId(s_Id);
			String totalCreditsString = student.getS_TotalCredits();
			float totalCredits = Float.parseFloat(totalCreditsString);
			int needCredits = professional.getP_LeastCredits();
			return totalCredits<needCredits;
		}
			return false;
	}
	
	public void selectUnPassElective(Students student, List<Elective> unPassElective, String grade) {
		
		for (Elective elective : unPassElective) {
			selectUnPassCourse(student,elective,grade);
		}
	}

	private void selectUnPassCourse(Students student, Elective elective, String grade) {
		String e_Id = elective.getE_Id();
		List<CourseScheduling> listCourseScheduling = student.getListCourseScheduling();
		CourseScheduling selectUnPassCourse = courseSchedulingDao.selectCourseSchedulingByEId(grade, e_Id);
		listCourseScheduling.add(selectUnPassCourse);
	}
	public void addDetailInfo(Students student,String grade){
		String classesId = student.getS_StudyClassesId();
		Professional professional = professionalDao.selectProfessionalByClassesId(classesId);
		String collegeName = collegeDao.selectCollegeNameByProfessionalId(professional.getP_cId());
		String teaPointName = teachingPointDao.selectTeaPointNameByClassesId(classesId);
		student.setProfessional(professional);
		student.setCollegeName(collegeName);
		student.setTeachingName(teaPointName);
		loardIsJinxiuStu(student,grade);
	}
	
	public void loardIsJinxiuStu(Students student,String grade) {
		int gradeInt = Integer.parseInt(grade);
		String s_Id = student.getS_Id();
		String s_ClassesId = student.getS_ClassesId();
		String s_StudyClassesId = student.getS_StudyClassesId();
		if (!s_ClassesId.equals(s_StudyClassesId)) {
			Students stu = studentsDao.selectStusById(s_Id);
			String s_JstuNum = stu.getS_StuNum();
			student.setS_JstuNum(s_JstuNum);
			
			Classes jStuClasses = classesDao.findClassesById(s_StudyClassesId);
			if (jStuClasses!=null&&!jStuClasses.equals(null)&&!jStuClasses.equals("")) {
				String jStuClassesName = jStuClasses.getC_Name();
				String jStuClassesNum = jStuClasses.getC_cId();
				student.setS_JClassName(jStuClassesName);
				student.setS_JClassNum(jStuClassesNum);
				
			}
		}
		Classes classes = classesDao.findClassesById(s_ClassesId);
		student.setClasses(classes);
	}
}
