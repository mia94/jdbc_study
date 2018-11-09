package jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_study.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();//유틸의 리스트 import, Department import//exception추가됨
}
