package edu.zjnu.graduation_statistics.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.zjnu.graduation_statistics.domain.dto.ResultInfo;
import edu.zjnu.graduation_statistics.utils.ReadExcel;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

public class UploadExcelServlet extends HttpServlet {

	// 缓冲区域
	File tempPathFile;
	// 默认路径
	String uploadTo = "D:\\";
	// 支持的文件类型
	String[] errorType = { ".xls" };
	
	// 格式化日期
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String erroMsg = "";
		try {
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			// 取得服务器真实路径
			uploadTo = req.getSession().getServletContext().getRealPath("\\") + "\\upload\\";
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓冲区大小，这里是4kb
			factory.setSizeThreshold(4096);
			// 设置缓冲区目录
			factory.setRepository(tempPathFile);
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint
			// 设置最大文件尺寸，这里是4MB
			upload.setSizeMax(4 * 1024 * 1024);
			// 开始读取上传信息
			List fileItems = new ArrayList();
			fileItems = upload.parseRequest(req);
			// 依次处理每个上传的文件
			Iterator iter = fileItems.iterator();
			System.out.println("fileItems的大小是" + fileItems.size());

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// 忽略其他不是文件域的所有表单信息
				System.out.println("正在处理" + item.getFieldName());
				if (!item.isFormField()) {
					String name = item.getName();
					long size = item.getSize();
					if ((name == null || name.equals("")) && size == 0)
						continue;
					boolean flag = false;
					for (int temp = 0; temp < errorType.length; temp++) {
						if (name.contains(errorType[temp])) {
							flag = true;
						}
					}
					if (!flag) {
						System.out.println("上传了不支持的文件类型" + name);
						erroMsg = "上传了不支持的文件类型,应上传.xls为后缀的Excel文件";
						throw new IOException(name + ": wrong type");

					}
					String fileName = "";
					String excelName = "";
					try {
						excelName = format.format(new Date()) + ".xls";
						fileName = uploadTo + excelName;
						System.out.println("fileName==============" + fileName);
						File file = new File(fileName);
						file.createNewFile();
						item.write(file);
						// 调用ReadExcel类进行读出excel
						// ReadExcel.readExcel();
						// System.out.println(name + "\t\t" + size);
					} catch (Exception e) {
						erroMsg = e.getMessage();
						e.printStackTrace();
					}
					if (erroMsg != "" && erroMsg != null && !erroMsg.isEmpty()) {
						System.out.println(erroMsg + "erroMsg1============");

						// req.setAttribute("tempExcel", fileName);
						// req.getRequestDispatcher("/WEB-INF/export.jsp").forward(req,
						// resp);
						System.out.println(excelName + "=========excelName");
						ResultInfo resultInfo = new ResultInfo(false, erroMsg, "上传文件失败");
						JSON json = JSONSerializer.toJSON(resultInfo);
						req.setAttribute("resultInfo", json.toString());
						req.getRequestDispatcher("/WEB-INF/exportExcel.jsp").forward(req, resp);
					} else {
						System.out.println(erroMsg + "erroMsg12============");
						ResultInfo resultInfo = new ResultInfo(true, "已成功上传文件", excelName);
						JSON json = JSONSerializer.toJSON(resultInfo);
						req.setAttribute("resultInfo", json.toString());
						req.getRequestDispatcher("/WEB-INF/exportExcel.jsp").forward(req, resp);

					}
				}
			}
		} catch (Exception e) {
			ResultInfo resultInfo = new ResultInfo(false, erroMsg, "上传文件失败");
			JSON json = JSONSerializer.toJSON(resultInfo);
			req.setAttribute("resultInfo", json.toString());
			req.getRequestDispatcher("/WEB-INF/exportExcel.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void init() throws ServletException {
		tempPathFile = new File("d:\\temp\\buffer\\");
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
	}

}
