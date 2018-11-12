package jdbc_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;//core안 들어가는 걸로, 주의

import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.jdbc.MySQLJdbcUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	Logger LOG = LogManager.getLogger();
	
	@Override
	public List<Employee> selectEmployeeByAll() {
		List<Employee> list = new ArrayList<>();
		String sql = "select empno, empname, title, manager,salary,dno from employee";
		
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LOG.debug(pstmt);
			while(rs.next()) {
				list.add(getEmployee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		String title = rs.getString("title");
		Employee manager = new Employee(rs.getInt("manager"));//직속상사의 사번이 들어가기 때문에 getInt
		int salary = rs.getInt("salary");
		Department dept =new Department(rs.getInt("dno"));
		return new Employee(empNo, empName, title,manager, salary, dept);
	}

	@Override
	public int insertEmployee(Employee emp) throws SQLException {
		String sql = "insert into employee values(?,?,?,?,?,?)";
		int res = 0;
		
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getTitle());
			pstmt.setInt(4, emp.getManager().getEmpNo());//manager 는 employee타입으로 반환되니까 manager의 사번만 빼서 사용
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getDeptNo());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteEmployee(Employee emp) throws SQLException {
		String sql = "delete from employee where empNo = ? ";
		int res = 0;
		
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, emp.getEmpNo());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		
		return res;
	}

	@Override
	public int updateEmployee(Employee emp) throws SQLException {
		String sql = "update employee set empname = ? where empno = ? ";
		int res = 0;
		
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, "우선미");
			pstmt.setInt(2, 1006);
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) throws SQLException {
		String sql = "select empno, empname, title, manager,salary,dno from employee where empno = ?";
		Employee selEmp = null;
		
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
				pstmt.setInt(1, emp.getEmpNo());
				LOG.debug(pstmt);
				try(ResultSet rs = pstmt.executeQuery();){
					if(rs.next()) {
						selEmp = getEmployee(rs);
					}
				}
		}
		
		return selEmp;
	}

}









