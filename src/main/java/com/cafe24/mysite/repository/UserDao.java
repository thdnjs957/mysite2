package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;


@Repository
public class UserDao {
	
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.211:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}
		
		return conn;
	}
	
	
	public boolean insert(UserVo vo) {

		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "insert into user values(null,?,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean update(UserVo vo) {
		
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "update user set name = ? , email=? , password=? where no = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setLong(4, vo.getNo());
			
			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}
	
	
	public UserVo get(Long no) {
		
		UserVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();

			String sql = "select name,email,password,gender,join_date from user where no = ?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString(1);
				String email = rs.getString(2);
				String password = rs.getString(3);
				String gender = rs.getString(4);
				String joinDate = rs.getString(5);
				
				//UserVo vo = new UserVo();//위에 있는걸로 해야지 여기서 new 하면 안됨
				result = new UserVo();
				result.setName(name);
				result.setNo(no);
				result.setEmail(email);
				result.setPassword(password);
				result.setGender(gender);
				result.setJoinDate(joinDate);
				
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public UserVo get(String email, String password) { //로그인 하니깐 하나 존재

		UserVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();

			String sql = "select no,name from user where email=? and password=?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {

				Long no = rs.getLong(1);
				String name = rs.getString(2);

				//UserVo vo = new UserVo();//위에 있는걸로 해야지 여기서 new 하면 안됨
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
}
