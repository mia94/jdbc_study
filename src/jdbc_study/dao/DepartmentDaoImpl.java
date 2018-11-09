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
		Connection conn = null;//java.sql connection으로 import
		PreparedStatement pstmt = null;
		ResultSet rs = null;//ResultSet import 시키기 (한개뿐)
		
		try {//예외처리 여기서 하기
			conn = MySQLJdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);//conn.해서 나오는 첫번째꺼
			LOG.debug(pstmt);//debug매개변수 한개있는걸로 선택, 반드시 여기서 pstmt 확인해봐야 함
			rs = pstmt.executeQuery();//rs가 커서와 같아서 컬럼명을 가르킴, 쿼리는 insert에 이용됨

			while(rs.next()) {//다음행이 있으면 true
				int deptNo = rs.getInt("deptno");//컬럼이름을 적어주는게 편함, select한 순서대로 적어야함
				String deptName = rs.getString("deptname");
				int floor = rs.getInt("floor");
				Department dept = new Department(deptNo, deptName, floor);
				list.add(dept);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//거꾸로 종료시키기
			try {//예외처리해주기
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list;//리스트를 리턴
	}

}




















