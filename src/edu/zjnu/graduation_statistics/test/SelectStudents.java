package edu.zjnu.graduation_statistics.test;

import java.util.List;

import edu.zjnu.graduation_statistics.domain.Students;
import edu.zjnu.graduation_statistics.service.StudentsService;

public class SelectStudents {

	
	public static void main(String[] args) {
		StudentsService studentsService = new StudentsService();
		List<Students> students=studentsService.selectStudentByExcelStudentNum();
		System.out.println(students);
	}
}
