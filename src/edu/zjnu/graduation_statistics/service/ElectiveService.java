package edu.zjnu.graduation_statistics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.zjnu.graduation_statistics.dao.ClassesDao;
import edu.zjnu.graduation_statistics.dao.CourseSchedulingDao;
import edu.zjnu.graduation_statistics.dao.ElectiveDao;
import edu.zjnu.graduation_statistics.dao.ProfessionalDao;
import edu.zjnu.graduation_statistics.domain.Elective;
import edu.zjnu.graduation_statistics.domain.Professional;
import edu.zjnu.graduation_statistics.domain.Students;

public class ElectiveService {


	ElectiveDao electiveDao = new ElectiveDao();
	ClassesDao classesDao = new ClassesDao();
	CourseSchedulingDao courseSchedulingDao = new CourseSchedulingDao();
	ProfessionalDao professionalDao = new ProfessionalDao();
	public List<Elective> selectUnPassElectiveForStudent(Students students, String grade) {

		String s_Id = students.getS_Id();

		List<Elective> unPassElectives = new ArrayList<Elective>();
		List<Elective> selectElectiveBySId = electiveDao.selectElectiveBySId(grade, s_Id);
		float totalCredits = 0;
		for (Elective elective : selectElectiveBySId) {
			if (isPass(elective)) {
				float credits = stringParseFloat(courseSchedulingDao.getCreditsByCourseId(elective.getE_csId()));
				totalCredits+=credits;
			} else {
				unPassElectives.add(elective);
			}
		}
		students.setS_TotalCredits(String.valueOf(totalCredits));
		return unPassElectives;
	}

	public boolean isPass(Elective elective) {
		String s_score1 = elective.getE_Score1();
		String s_score2 = elective.getE_Score2();
		String s_score3 = elective.getE_Score3();
		String s_score4 = elective.getE_Score4();
		String s_score5 = elective.getE_Score5();
		String s_score6 = elective.getE_Score6();
		String s_score7 = elective.getE_Score7();
		String s_score = s_score1 + s_score2 + s_score3 + s_score4 + s_score5 + s_score6 + s_score7;
		if (!s_score.trim().isEmpty()) {
			if (isNumber(s_score)) {
				boolean isPassScore = isPassScore(s_score1, s_score2, s_score3, s_score4, s_score5, s_score6, s_score7);
				if (isPassScore) {
					return true;
				} else {
					return false;
				}
			} else {
				if (isPassLevel(s_score)) {
					return true;
				} else {
					return false;
				}

			}
		} else {
			return false;
		}
	}

	public boolean isPassScore(String s_score1, String s_score2, String s_score3, String s_score4, String s_score5,
			String s_score6, String s_score7) {
		float[] scoreArrs = new float[7];

		insertArray(scoreArrs, s_score1, 0);
		insertArray(scoreArrs, s_score2, 1);
		insertArray(scoreArrs, s_score3, 2);
		insertArray(scoreArrs, s_score4, 3);
		insertArray(scoreArrs, s_score5, 4);
		insertArray(scoreArrs, s_score6, 5);
		insertArray(scoreArrs, s_score7, 6);
		
		float scoreMax = getMax(scoreArrs);
		if (scoreMax < 60) {
			return false;
		} else {
			return true;
		}
	}

	public void insertArray(float[] scoreArrs,String score,int i){
		if (score != null && !score.trim().isEmpty()) {
			if (isNumber(score)) {
				float score1 = stringParseFloat(score);
				scoreArrs[i] = score1;
			}else {
				if (isPassLevel(score)) {
					scoreArrs[i] = Float.parseFloat("60");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static float getMax(float[] arr) {
		float max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	public boolean isNumber(String s_score) {
		String regex = "[0-9]+?";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s_score);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPassLevel(String s_score){
		String regex = "[A-D]+?";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s_score);
		
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}
	
	public float stringParseFloat(String string) {
		float f = 0;
		if (string.trim().isEmpty()) {
			f = 0;
		} else {
			f = Float.parseFloat(string);
		}
		return f;
	}

}
