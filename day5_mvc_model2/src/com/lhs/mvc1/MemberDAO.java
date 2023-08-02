package com.lhs.mvc1;

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

}
