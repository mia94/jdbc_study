package jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_study.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();//유틸의 리스트 import, Department import//exception추가됨
	//insert를 성공여부를 숫자로 리턴해줌
	int insertDepartment(Department department) throws SQLException;
}
