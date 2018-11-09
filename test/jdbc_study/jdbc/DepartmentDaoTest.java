package jdbc_study.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javafx.scene.effect.Shadow;
import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DepartmentDaoImpl;
import jdbc_study.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("setUpBeforeClass()");
		dao = new DepartmentDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("tearDownAfterClass()");
		dao = null;
	}

	@Test
	public void test01SelectDepartmentByAll() {
		List<Department> list = dao.selectDepartmentByAll();
		for(Department dept : list) {
			MySQLJdbcUtilTest.LOG.debug(dept);
		}
		
		Assert.assertNotEquals(0, list.size());//괄호안의 두 값이 달라야(not equals) 성공한것
	}
	@Test
	public void test02InsertDepartment() {
		Department newDept = new Department(4, "자바개발부서", 15);
		try {
			int res = dao.insertDepartment(newDept);
			Assert.assertEquals(1, res);//기대값과 리턴값이 같아야 추가됐다는 의미
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());//에러코드 번호로 출력
			e.printStackTrace();
			if(e.getErrorCode()==1062) {
				JOptionPane.showMessageDialog(null, "해당부서는 이미 존재합니다.");
			}
		}
	}
	
	@Test
	public void test04DeleteDepartment() {
		Department delDept = new Department(4);
		try {
			int res = dao.deleteDepartment(delDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "삭제실패");
		}
		test01SelectDepartmentByAll();
	}
	
	@Test
	public void test03UpdateDepartment() {
		Department updateDept = new Department();
		try {
			int res = dao.updateDepartment(updateDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "수정실패");
		}
		test01SelectDepartmentByAll();
		
	}
	
	@Test
	public void test05SelectDepartment() {
		Department selDept = new Department(1);
		try {
			Department res = dao.selectDepartmentByNo(selDept);
			JOptionPane.showMessageDialog(null, res);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"검색 실패");
		}
	}

}










