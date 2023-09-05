package com.jaerong.controller;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface BoardController {
	public String getArticleList(Model model) throws Exception;
	public String wirteArticle() throws Exception;
	public String addArticle(@RequestParam(value = "i_title") String title,
			@RequestParam(value = "i_content") String content) throws Exception;
	public String viewArticle(Model model, @RequestParam(value = "no") String articleNo) throws Exception;
	public String editArticle(@RequestParam(value = "articleNO") String articleNo,
			@RequestParam(value = "title") String title, @RequestParam(value = "content") String content,
			RedirectAttributes redirectAttr) throws Exception;
	public String removeArticle(@RequestParam(value = "articleNo") String articleNo) throws Exception;
	
	public String viewImage() throws Exception;
	
	public Map<String, String> changeImage() throws Exception;
	
	public String showImage(Model model)throws Exception;
	public Map<String, String> getImage(@RequestPart(value="uploadfile") MultipartFile uploadfile) throws Exception;
}

