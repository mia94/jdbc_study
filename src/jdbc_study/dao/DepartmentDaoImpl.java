package jdbc_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_study.dto.Department;
import jdbc_study.jdbc.MySQLJdbcUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	Logger LOG = LogManager.getLogger();//import apache로 골라서 하기
	
	@Override
	public List<Department> selectDepartmentByAll(){//exception빨간줄 뜬거 dao로 넘기지X
		List<Department> list = new ArrayList<>();
		String sql = "select deptno, deptname, floor from department";//select했을때의 컬럼부른 순서가 중요함!!
		
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LOG.debug(pstmt);
			while(rs.next()) {//다음행이 있으면 true
				list.add(getDepartment(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;//리스트를 리턴
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptName");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public int insertDepartment(Department department) throws SQLException {
		String sql = "insert into department values(?,?,?)";
		int res = 0;
		
		try(Connection conn = MySQLJdbcUtil.getConnection();//순서 꼭 맞춰서 써야함
				PreparedStatement pstmt = conn.prepareStatement(sql);){//아직 ?상태로 비어있기 때문에 Re X
			pstmt.setInt(1, department.getDeptNo());//첫번째 매개변수라는 의미로 1이 들어감
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());//?에 값을 다 넣음
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();//executeUpdate 리턴타입이 int
		}
		
		return res;
	}

	@Override
	public int deleteDepartment(Department department) throws SQLException {
		String sql = "delete from department where deptNo = ? ";
		int res = 0;
		
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1,department.getDeptNo());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		
		return res;
	}

	@Override
	public int updateDepartment(Department department) throws SQLException {
		String sql = "update department set deptName = ?, floor = ? where deptNo = ?";
		int res = 0;
		
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, "자바테스트");
			pstmt.setInt(2, 20);
			pstmt.setInt(3, 5);
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		
		return res;
	}

	@Override
	public Department selectDepartmentByNo(Department department) throws SQLException {
		Department seldept = null;
		String sql = "select deptNo, deptName, floor from department where deptNo = ?";
		
		try(Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, department.getDeptNo());//괄호안에 들어갈수 없는 명령
			LOG.debug(pstmt);
			try(ResultSet rs = pstmt.executeQuery();){//나중에 날려줘야 함
				if (rs.next()) {
					seldept = getDepartment(rs);
				}
			}
			
		}

		return seldept;
	}

}




















