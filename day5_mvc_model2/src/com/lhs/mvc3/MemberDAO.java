package com.lhs.mvc3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	private DataSource dataSource;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public MemberDAO() {
	    try {
	        Context context = new InitialContext(); 
	        Context envContext = (Context) context.lookup("java:/comp/env"); 
	        dataSource = (DataSource) envContext.lookup("jdbc/oracle");
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<MemberVO> listMembers() {
		List<MemberVO> members = new ArrayList<MemberVO>();
		String query = "select * from t_member order by joinDate desc";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				members.add(new MemberVO(id, pwd, name, email, joinDate));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	public void addMember(MemberVO member) {
		String query = "insert into t_member(id, pwd, name, email) values (?, ?, ?, ?)";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getId()); 
			pstmt.setString(2, member.getPwd()); 
			pstmt.setString(3, member.getName()); 
			pstmt.setString(4, member.getEmail()); 
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public MemberVO findMember(String id) {
		MemberVO member = new MemberVO();
		String query = "select * from t_member where id = ?";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			member.setId(rs.getString("id"));
			member.setPwd(rs.getString("pwd"));
			member.setName(rs.getString("name"));
			member.setEmail(rs.getString("email"));
			member.setJoinDate(rs.getDate("joinDate"));
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	public void modMember(MemberVO member) {
		String query = "update t_member set pwd = ?, name = ?, email = ? where id = ?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getId());
			pstmt.executeQuery();
			pstmt.close();
			conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delMember(String id) {
		String query = "delete from t_member where id = ?";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
