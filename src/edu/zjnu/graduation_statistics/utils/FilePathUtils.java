package edu.zjnu.graduation_statistics.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

public class FilePathUtils {
	
	public static String getPath(String fileName){
		String abstractPath = FilePathUtils.class.getClassLoader().getResource("c3p0-config.xml").getPath();
		File file = new File(abstractPath);
		String parent = file.getParent();
		String path = parent+"\\templateFile\\"+fileName;
		
		return path;
	}
	
	public static File getFile(String fileName){
		File file = new File(getPath(fileName));
		try {
			if(!file.getParentFile().exists()){
				file.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("getFile我错了"+e.getMessage());
		}
		return file;
	}
	
	public static FileInputStream getFileInputStream(String fileName){
		try {
			return new FileInputStream(getFile(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("getFileInputStream我错了"+e.getMessage());
		}
		return null;
	}
	
	public static FileOutputStream getFileOutputStream(String fileName){
		try {
			return new FileOutputStream(getFile(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("getFileOutputStream我错了"+e.getMessage());
		}
		return null;
	}
}
