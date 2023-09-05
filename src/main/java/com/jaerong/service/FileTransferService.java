package com.jaerong.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileTransferService {
	public String webToLocal(MultipartFile file) throws IOException {
		String check;
		double doubleCheck;
//		String baseUrl = "D://input_image/";
		String baseUrl = "/home/ubuntu/my_service/image_repo/";
		if (!file.isEmpty()) {

			File newFileName = new File(baseUrl + file.getOriginalFilename());

			// 전달된 내용을 실제 물리적인 파일로 저장
			file.transferTo(newFileName);

			check = RequestUtil.restRequest("http://localhost:8000/catdog/predict/",
					baseUrl + file.getOriginalFilename());

			System.out.println("result from Django:" + check);
			doubleCheck = Double.parseDouble(check);

			if (doubleCheck < 0.5) {
				return "cat";
			} else {
				return "dog";
			}
		}
		return "error";
	}
}
