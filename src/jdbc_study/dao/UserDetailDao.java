package jdbc_study.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import jdbc_study.dto.UserDetail;

public interface UserDetailDao {
	int insertDetail(UserDetail userDetail) throws SQLException;
	int deleteUserDetail(UserDetail userDetail) throws SQLException; 
	UserDetail selectUserDetail(UserDetail userDetail) throws SQLException; 
	String getPictureByUserDetail(UserDetail userDetail, String filePath) throws SQLException, FileNotFoundException,IOException;

}
