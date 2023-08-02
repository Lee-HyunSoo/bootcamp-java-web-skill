package com.lhs.board8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<Integer> removeArticle(int articleNO) {
		List<Integer> articles = boardDAO.selectRemovedArticles(articleNO);
		boardDAO.deleteArticle(articleNO);
		return articles;
	}
	
	public int addReply(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}

	public Map<String, Object> listArticles(Map<String, Integer> pagingMap) {
		Map<String, Object> articlesMap = new HashMap<String, Object>();
		List<ArticleVO> articles = boardDAO.selectAllArticles(pagingMap); // section 1에서 1페이지에 해당하는 글들
		int totArticles = boardDAO.selectTotArticles();
		articlesMap.put("articles", articles);
		articlesMap.put("totArticles", totArticles);
//		articlesMap.put("totArticles", 170);

		return articlesMap;
	}
	
}