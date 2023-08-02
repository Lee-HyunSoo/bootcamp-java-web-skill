package com.lhs.board1;

import java.util.List;

public class BoardService {
	
	private final BoardDAO boardDAO = new BoardDAO();

	public BoardService() {
	}

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
}