package com.lhs.board5;

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
	
	public ArticleVO viewArticle(int articleNO) {
		return boardDAO.selectArticle(articleNO);
	}
	
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}

}