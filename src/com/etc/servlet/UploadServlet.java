package com.etc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	
	//将文件上传到该路径路径
	private static final String UPLOAD_DIRECTORY = "upload\\test";
	//上传配置
	//上传时的临时空间的大小
	private static final int MERORY_THRESHOLD = 1024*1024*3;//3MB
	//文件上传时的大小
	private static final int MAX_FILE_SIZE = 1024*1024*40;//40MB
	//上传请求的大小
	private static final int MAX_REQUEST_SIZE = 1024*1024*50;//50MB

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//判断是否是上传文件的操作
		if(!ServletFileUpload.isMultipartContent(request)){
			request.setAttribute("message", "你这个不是上传文件的操作");
			request.getRequestDispatcher("upload.jsp").forward(request, response);
		}else{
			//配置上传参数
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置内存临界值-如果超过后将产生临时文件并存储到临时目录中
			factory.setSizeThreshold(MERORY_THRESHOLD);
			//设置临时存储文件的目录
			System.out.println(System.getProperty("java.io.tmpdir"));
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//设置最大文件上传值
			upload.setFileSizeMax(MAX_FILE_SIZE);
			//设置最大请求值(包含表单数据和文件)
			upload.setSizeMax(MAX_REQUEST_SIZE);
			
			upload.setHeaderEncoding("UTF-8");
			
			//构造临时路径来存储上传的文件
			String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
			
			File uploadDir = new File(uploadPath);
			//如果目录不存在则创建
			if(!uploadDir.exists()){
				uploadDir.mkdir();
			}
			
			try {
				List<FileItem> formItems = upload.parseRequest(request);
				if(formItems != null && formItems.size() > 0){
					for(FileItem item : formItems){
						if(!item.isFormField()){
							String fileName = new File(item.getName()).getName();
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							System.out.println(filePath);
							item.write(storeFile);
							request.setAttribute("message","文件上传成功！");
						}else{
							System.out.println(item.getFieldName());
							System.out.println(new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("upload.jsp").forward(request, response);
	}

}
