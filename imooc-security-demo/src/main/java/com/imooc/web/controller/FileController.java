package com.imooc.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.web.dto.FileInfo;


@RestController
@RequestMapping("/file")
public class FileController {
	
	private static String folder="D:/liyanworkspace/imooc-security-demo/src/main/java/com/imooc/web/controller";

	@PostMapping
	public FileInfo uploadFile(MultipartFile file) throws IllegalStateException, IOException{
		
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		File localFile=new File(folder,new Date().getTime()+".txt");
		//将上传的文件传到本地文件
		file.transferTo(localFile);
		
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	@GetMapping("/{id}")
	public void download(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		
		try(InputStream inputStream=new FileInputStream(new File(folder,id+".txt"));
				OutputStream outputStream=response.getOutputStream();
				) {
			IOUtils.copy(inputStream, outputStream);
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment;filename=test1.txt");
			outputStream.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}

}
