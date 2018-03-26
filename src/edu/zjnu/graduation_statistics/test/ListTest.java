package edu.zjnu.graduation_statistics.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;

import com.google.common.base.Joiner;

public class ListTest {

	public static void main(String[] args) {
		
		List<String> studentNumList = new ArrayList<String>();
		studentNumList.add("12345667");
		studentNumList.add("12345666");
		studentNumList.add("12345666");
		studentNumList.add("12345665");
		studentNumList.add("12345664");
		String num = Joiner.on(",").join(studentNumList);
		
		System.out.println(num);
		
		
	}

}
