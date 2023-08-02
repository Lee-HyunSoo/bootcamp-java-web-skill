package com.lhs.board2;

import java.util.List;

public class BoardService {
	
	private final BoardDAO boardDAO = new BoardDAO();

	public BoardService() {
	}

	public List<ArticleVO> listArticles() {
		return boardDAO.selectAllArticles();
	}

	public void addArticle(ArticleVO article) {
		boardDAO.insertNewArticle(article);
	}

}