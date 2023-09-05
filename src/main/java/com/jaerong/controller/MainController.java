package com.jaerong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jaerong.model.OnboardDto;
import com.jaerong.service.BoardService;
import com.jaerong.service.FileTransferService;

@Controller
@RequestMapping("/board")
public class MainController implements BoardController {
	//name attribute를 MainController가 아닌 BoardController로 분리
	
	@Autowired
	BoardService boardService;
	@Autowired
	OnboardDto onboardDto;
	@Autowired
	FileTransferService fileTransferService;

	List<OnboardDto> articleList;
	
	Logger logger = LoggerFactory.getLogger("com.jaerong.controller.BoardController");

	@Override
	@RequestMapping({ "/list", "/" })
	public String getArticleList(Model model) throws Exception {
		articleList = boardService.listArticles();
		model.addAttribute("data_list", articleList);
		return "list";
	}

	@Override
	@RequestMapping("/add")
	public String wirteArticle() throws Exception {
		return "write";
	}

	@Override
	@PostMapping("/addArticle")
	public String addArticle(String title, String content) throws Exception {
		onboardDto.setTitle(title);
		onboardDto.setContent(content);
		onboardDto.setWrite_id("bit");
		boardService.addArticle(onboardDto);
		return "redirect:list";
	}

	@Override
	@GetMapping("/view")
	public String viewArticle(Model model, String articleNo) throws Exception {
		onboardDto = boardService.viewArticle(Integer.parseInt(articleNo));
		model.addAttribute("article", onboardDto);
		logger.trace("trace==>articleNo: " + articleNo);
		logger.debug("debug==>articleNo: " + articleNo);
		logger.info("info==>articleNo: " + articleNo);
		logger.warn("warn==>articleNo: " + articleNo);
		logger.error("error==>articleNo: " + articleNo);
		return "view";
	}

	@Override
	@PostMapping("/edit")
	public String editArticle(String articleNo, String title, String content, RedirectAttributes redirectAttr)
			throws Exception {
		onboardDto.setArticle_no(Integer.parseInt(articleNo));
		onboardDto.setTitle(title);
		onboardDto.setContent(content);
		boardService.editArticle(onboardDto);
		redirectAttr.addAttribute("no", articleNo);
		return "redirect:view";
	}

	@Override
	@PostMapping("/remove")
	public String removeArticle(String articleNo) throws Exception {
		boardService.removeArticle(Integer.parseInt(articleNo));
		return "redirect:list";
	}

	@Override
	@GetMapping("/show")
	public String viewImage() throws Exception {
		// TODO Auto-generated method stub
		return "show";
	}

	@Override
	@PostMapping("/change")
	@ResponseBody
	public Map<String, String> changeImage() throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("file", "/image/cat1.jpg");
		map.put("name", "cat");
		return map;
	}

	@Override
	@GetMapping("/catdog")
	public String showImage(Model model) throws Exception {
		return "dog_cat";
	}

	@Override
	@PostMapping("/upload")
	@ResponseBody
	public Map<String, String> getImage(MultipartFile uploadfile) throws Exception {
		Map<String, String> map = new HashMap<>();
		String fromDjango = fileTransferService.webToLocal(uploadfile);
		map.put("catordog", fromDjango);
		return map;
	}	
}
