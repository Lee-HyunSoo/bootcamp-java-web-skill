package com.lhs;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.Context;

public class MemberDAO {

	/*
	// 추후, Server의 context.xml에 미리 선언할 예정
	private static final String driver = "oracle.jdbc.driver.OracleDriver"; // ojdbc6.jar 필요
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "scott";
	private static final String pwd = "tiger";
	*/

	private Connection con;
//	private Statement stmt;
	private PreparedStatement pstmt;
	private DataSource dataSource;
	
	public MemberDAO() {
		System.out.println("MemberDAO 생성자 호출");
		try {
			// context.lookup의 반환타입은 Object 이다. 때문에 형변환을 해야한다.
			Context context = new InitialContext(); // context.xml
			Context envContext = (Context) context.lookup("java:/comp/env"); // <Context> 를 찾기 위한 경로
			dataSource = (DataSource) envContext.lookup("jdbc/oracle"); // <Context> 내부 <Resource> 를 찾기 위해
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
			
//			stmt = con.createStatement();
//			System.out.println("Statement 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public List<MemberVO> findAll() {
//		connDB();
		List<MemberVO> members = new ArrayList<MemberVO>();
		String query = "select * from t_member";
		
		try {			
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {	
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
				members.add(memberVO);
			}
			rs.close();
			pstmt.close();
			con.close(); // Connection Pool에 사용한 Connection 객체 반납
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}
	
	public void addMember(MemberVO memberVO) {
		String query = "insert into t_member(ID, PWD, NAME, EMAIL) values (?, ?, ?, ?)";
		
		try {			
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPwd());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delMember(String id) {
		String query = "delete from t_member where id = ?";
		
		try {			
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modifyMember(MemberVO memberVO) {
		String query = "update t_member set PWD = ?, NAME = ?, EMAIL = ? where ID = ?";
		
		try {			
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberVO.getPwd());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getEmail());
			pstmt.setString(4, memberVO.getId());
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberVO findById(String id) {
//		connDB();
		MemberVO member = new MemberVO();
		String query = "select * from t_member where id = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);	
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			// 조회 예상값이 하나일 땐 while 빼고 if로 바꿔도 된다.
			if(rs.next()) {
				member.setId(rs.getString("id"));
				member.setPwd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setJoinDate(rs.getDate("joinDate"));
			}
			rs.close();
			pstmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public int countRecords() {
//		connDB();
		int count = 0;
		String query = "select count(*) from t_member";
		
		try {	
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);	
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
