package jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_study.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();//유틸의 리스트 import, Department import//exception추가됨
	//insert를 성공여부를 숫자로 리턴해줌
	int insertEmployee(Employee emp) throws SQLException;
	
	int deleteEmployee(Employee emp) throws SQLException;
	int updateEmployee(Employee emp) throws SQLException;
	Employee selectEmployeeByNo(Employee emp) throws SQLException;
}



