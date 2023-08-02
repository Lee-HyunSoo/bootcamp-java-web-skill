package com.lhs.board7;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataSource;
	private Connection conn;
	private PreparedStatement pstmt;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate" + " from t_board"
				+ " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
				+ " ORDER SIBLINGS BY articleNO DESC";
		try {
			conn = dataSource.getConnection();
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVO article = new ArticleVO();
				article.setLevel(rs.getInt("level"));
				article.setArticleNO(rs.getInt("articleNO"));
				article.setParentNO(rs.getInt("parentNO"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setId(rs.getString("id"));
				article.setWriteDate(rs.getDate("writeDate"));
				articles.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();
		String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id) VALUES (?, ? ,?, ?, ?, ?)";
		try {
			conn = dataSource.getConnection();
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, article.getParentNO());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			pstmt.setString(5, article.getImageFileName());
			pstmt.setString(6, article.getId());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	}
	
	private int getNewArticleNO() {
		String query = "SELECT max(articleNO) from t_board";
		try {
			conn = dataSource.getConnection();
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next())
				return (rs.getInt(1) + 1);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		String query = "select articleNO, parentNO, title, content, NVL(imageFileName, 'null') as imageFileName, id, writeDate "
				+ "from t_board where articleNO = ?";
		try {
			conn = dataSource.getConnection();
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			String imageFileName = URLEncoder.encode(rs.getString("imageFileName"), "UTF-8"); // 파일이름에 특수문자가 있을 경우 인코딩
			if (imageFileName.equals("null")) {
				imageFileName = null;
			}
	
			article.setArticleNO(rs.getInt("articleNO"));
			article.setParentNO(rs.getInt("parentNO"));
			article.setTitle(rs.getString("title"));
			article.setContent(rs.getString("content"));
			article.setImageFileName(imageFileName);
			article.setId(rs.getString("id"));
			article.setWriteDate(rs.getDate("writeDate"));
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		try {
			conn = dataSource.getConnection();
			String query = "update t_board set title=?, content=?";
			if (imageFileName != null && imageFileName.length() != 0) {
				query += ",imageFileName=?";
			}
			query += " where articleNO=?";
			
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			if (imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			} else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteArticle(int articleNO) {
		try {
			conn = dataSource.getConnection();
			String query = "DELETE FROM t_board WHERE articleNO in (SELECT articleNO FROM t_board START WITH articleNO = ? CONNECT BY PRIOR articleNO = parentNO)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Integer> selectRemovedArticles(int articleNO) {
		List<Integer> articleNOList = new ArrayList<Integer>();
		try {
			conn = dataSource.getConnection();
			String query = "SELECT articleNO FROM t_board START WITH articleNO = ? CONNECT BY PRIOR  articleNO = parentNO";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleNO = rs.getInt("articleNO");
				articleNOList.add(articleNO);
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNOList;
	}

}