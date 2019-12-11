package com.bawei.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
public class FileUtils {
	/**
	 * 文件上传
	 * 
	 * @param uploadFile 文件上传对象
	 * @param request 请求对象
	 * @return 文件存放的相对路径
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String upload(MultipartFile uploadFile, HttpServletRequest request)
			throws IllegalStateException, IOException {

		String filePath = "";
		String originalFilename = "";
		String uuid = "";
		String realPath = "";
		if (!uploadFile.isEmpty()) {
			// 服务器上真实的路径
			realPath = request.getSession().getServletContext().getRealPath("/upload/");
			// 文件的原始名称
			originalFilename = uploadFile.getOriginalFilename();
			// 为了保证文件名的唯一性
			uuid = UUID.randomUUID().toString().replace("-", "");
			//if (originalFilename.endsWith("png") || originalFilename.endsWith("jpg")) {
				File file = new File(
						realPath + "\\" + uuid + originalFilename.substring(originalFilename.lastIndexOf(".")));
				uploadFile.transferTo(file);
			//}
		}
		// 文件在本地磁盘中
		filePath = "\\upload" + "\\" + uuid + originalFilename.substring(originalFilename.lastIndexOf("."));
		return filePath;
	}

	/***
	 * 下载方法
	 * 
	 * @param filepath
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static String download(String filepath, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;
		try {
			// 如果是从服务器上取就用这个获得系统的绝对路径方法。
			// String filepath = request.getRealPath(filepatha);//方法过时了
			// String filepathall =
			String realPath=request.getSession().getServletContext().getRealPath("/");

			File uploadFile = new File(realPath+filepath);

			fis = new FileInputStream(uploadFile);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);
			// 得到文件名
			String filename = filepath.substring(filepath.lastIndexOf("\\") + 1);

			// 这个就就是弹出下载对话框的关键代码
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));

			int bytesRead = 0;
			// 用输出流去写，缓冲输入输出流
			byte[] buffer = new byte[8192];
			while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
				}
				if (fis != null) {
					fis.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
