package com.es.zumeh.server.facade;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 6672251749213313343L;
	private static final String UPLOAD_DIRECTORY = "C:\\images";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		File uploadedFile = null;
		
		// process only multipart requests
		if (ServletFileUpload.isMultipartContent(req)) {

			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			List<FileItem> items;
			try {
				items = upload.parseRequest(req);
				for (FileItem item : items) {
					// process only file upload - discard other form item types
					if (item.isFormField())
						continue;

					String fileName = item.getName();
					// get only the file name not whole path
					if (fileName != null) {
						fileName = FilenameUtils.getName(fileName);
					}
					uploadedFile = new File(UPLOAD_DIRECTORY, fileName);
					item.write(uploadedFile);
					
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().print(uploadedFile.getAbsolutePath());
					resp.flushBuffer();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		//resp.setContentType("image/jpeg");

		// your image servlet code here
		String cteImages = "C:\\images\\";

		// Set content size
		String teste = cteImages + req.getParameter("imagePath");
		System.out.println("DEBUG SAIDA DE QUALQUER COISA: " + teste);
		File file = new File(teste);
		resp.setContentLength((int) file.length());

		// Open the file and output streams
		FileInputStream in = new FileInputStream(file);
		OutputStream out = resp.getOutputStream();

		// Copy the contents of the file to the output stream
		byte[] buf = new byte[1024];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		in.close();
		out.close();
	}
}