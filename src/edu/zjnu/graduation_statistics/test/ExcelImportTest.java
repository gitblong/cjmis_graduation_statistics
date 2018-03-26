package edu.zjnu.graduation_statistics.test;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.google.common.base.Joiner;

import edu.zjnu.graduation_statistics.utils.ImportExeclDao;

public class ExcelImportTest {

	
	public static void main(String[] args) {
		String read = "";
		try {
			read = new ImportExeclDao().read("D:/DevelopTools/apache-tomcat-8.0.38/webapps/cjmis_graduation_statistics/upload/20171129003426227.xls");
			System.out.println("？？？？？？？？？？？？？？？？？？");
			System.out.println("numString======"+read);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
