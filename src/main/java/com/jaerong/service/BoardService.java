package com.jaerong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaerong.model.BoardDao;
import com.jaerong.model.OnboardDto;

@Service // 인터페이스로 할 경우에는 이름을 못찾을 수 있기 때문에 반드시 ("boardService")를 등록해주어야한다.
         // 하지만 클래스로는 소문자로 바로 자동 변환 되긴 때문에 등록해주지 않아도 된다.
public class BoardService {
	@Autowired
	BoardDao boardDao;
	
	public List<OnboardDto> listArticles(){
		List<OnboardDto> articleList = boardDao.selectAllArticles();
		return articleList;
	}
	
	public void addArticle(OnboardDto onboardDto) {
		boardDao.insertArticle(onboardDto);
	}
	
	public OnboardDto viewArticle(int articleNo) {
		OnboardDto article = boardDao.selectArticle(articleNo);
		return article;
	}
	
	public void editArticle(OnboardDto onboardDto) {
		boardDao.updateArticle(onboardDto);
	}
	
	public void removeArticle(int articleNo) {
		boardDao.deleteArticle(articleNo);
	}
}
