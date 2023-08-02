package com.lhs.board3;

import java.util.List;

public class BoardService {
	private BoardDAO boardDAO = new BoardDAO();

	public BoardService() {
	}

	public List<ArticleVO> listArticles() {
		return boardDAO.selectAllArticles();
	}

	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}

}