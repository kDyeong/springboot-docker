package com.jaerong.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BoardDao {
	public List<OnboardDto> selectAllArticles() throws DataAccessException;
	public void insertArticle(OnboardDto onboardDto) throws DataAccessException;
	public OnboardDto selectArticle(int articleNo) throws DataAccessException;
	public void updateArticle(OnboardDto onboardDto) throws DataAccessException;
	public void deleteArticle(int articleNo) throws DataAccessException;
}
