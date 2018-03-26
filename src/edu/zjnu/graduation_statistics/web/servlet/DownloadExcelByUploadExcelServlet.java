package edu.zjnu.graduation_statistics.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import cn.itcast.commons.CommonUtils;
import edu.zjnu.graduation_statistics.domain.Students;
import edu.zjnu.graduation_statistics.domain.dto.FromName;
import edu.zjnu.graduation_statistics.domain.dto.ResultInfo;
import edu.zjnu.graduation_statistics.service.StudentsService;
import edu.zjnu.graduation_statistics.utils.FilePathUtils;
import edu.zjnu.graduation_statistics.utils.ImportExeclDao;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

public class DownloadExcelByUploadExcelServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String excelName = request.getParameter("excelName");
		StudentsService studentsService = new StudentsService();
		System.out.println("excelName====="+excelName);
		String uploadTo = request.getSession().getServletContext().getRealPath("\\") + "\\upload\\";
		String filePath = uploadTo + excelName;
		System.out.println("filePath==============="+filePath);
		String studentNumbers = "";
		try {
			studentNumbers = new ImportExeclDao().read(filePath);
			List<Students> unPassStudentList = studentsService.selectUnPassStudentsExcelNum(studentNumbers);
			JSON unPassStudentsJson = JSONSerializer.toJSON(unPassStudentList);
			String fileName = String.valueOf(System.currentTimeMillis())+".json";
			FileUtils.writeStringToFile(FilePathUtils.getFile(fileName), unPassStudentsJson.toString(), "utf-8");
			ResultInfo resultInfo = new ResultInfo(true,"无",fileName);
			JSON resultJsonString = JSONSerializer.toJSON(resultInfo);
			FileUtils.deleteQuietly(new File(filePath));
			response.getWriter().write(resultJsonString.toString());
		} catch (InvalidFormatException e) {
			ResultInfo resultInfo = new ResultInfo(false,e.getMessage(),e.getMessage());
			JSON resultInfoJson = JSONSerializer.toJSON(resultInfo);
			String resultJsonString = resultInfoJson.toString();
			System.out.print(resultJsonString);
			FileUtils.deleteQuietly(new File(filePath));
			e.printStackTrace();
			response.getWriter().write(resultJsonString);;
		}
		
	}

}
