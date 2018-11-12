package jdbc_study.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc_study.dto.Gender;
import jdbc_study.dto.UserDetail;
import jdbc_study.jdbc.LogUtil;
import jdbc_study.jdbc.MySQLJdbcUtil;

public class UserDetailDaoImpl implements UserDetailDao {
	
	
	@Override
	public int insertDetail(UserDetail userDetail) throws SQLException {
		LogUtil.prnLog("insertUserDetail()");
		String sql = "insert into user_detail(id, name, gender, pic, bio) values(?, ?, ?, ?, ?)";
		try (Connection conn = MySQLJdbcUtil.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) { 
			LogUtil.prnLog(pstmt); 
			pstmt.setInt(1, userDetail.getId()); 
			pstmt.setString(2, userDetail.getName()); 
			pstmt.setInt(3, userDetail.getGender().ordinal()); 
			pstmt.setBytes(4, userDetail.getPic()); 
			pstmt.setString(5, userDetail.getBio()); 
			LogUtil.prnLog(userDetail.toString()); 
			return pstmt.executeUpdate();
		}
	}

	@Override
	public int deleteUserDetail(UserDetail userDetail) throws SQLException {
		LogUtil.prnLog("deleteEmployee()"); 
		String sql = "delete from user_detail where id=?";
		try (Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
					pstmt.setInt(1, userDetail.getId());
					LogUtil.prnLog(pstmt);
					return pstmt.executeUpdate();
				}
	}

	@Override
	public UserDetail selectUserDetail(UserDetail userDetail) throws SQLException {
		LogUtil.prnLog("selectUserDetail()"); 
		String sql = "SELECT id, name, gender, bio FROM user_detail where id = ?";
		UserDetail searchUser = null; 
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, userDetail.getId());
			LogUtil.prnLog(pstmt);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					searchUser = getUserDetail(rs);
				}
			}
		}
		return searchUser;
	}

	private UserDetail getUserDetail(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		Gender gender = rs.getInt("gender")==0?Gender.FEMALE:Gender.MALE;
		String bio = rs.getString("bio");
		return new UserDetail(id, name, gender, bio);
	}

	@Override
	public String getPictureByUserDetail(UserDetail userDetail, String filePath)
			throws SQLException, FileNotFoundException, IOException {
		
		LogUtil.prnLog("getPictureByUserDetail()");
		String sql = "select pic from user_detail where id = ?"; 
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, userDetail.getId());
			try(ResultSet rs = pstmt.executeQuery();
					FileOutputStream output = new FileOutputStream(filePath)){
				if(rs.next()) {
					try(InputStream input = rs.getBinaryStream("pic")){
						byte[] buffer = new byte[1024];
						while (input.read(buffer)>0) {
							output.write(buffer);
						}
					}
				}
			}
		}
		return filePath;
	}

}











