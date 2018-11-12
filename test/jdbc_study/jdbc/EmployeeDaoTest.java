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

import jdbc_study.dao.EmployeeDao;
import jdbc_study.dao.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static EmployeeDao dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("setUpBeforeClass()");
		dao = new EmployeeDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("tearDownAfterClass()");
		dao = null;
	}

	@Test
	public void test01SelectEmployeeByAll() {
		List<Employee> list = dao.selectEmployeeByAll();
		for(Employee e:list) {
			//System.out.println(e);
			MySQLJdbcUtilTest.LOG.debug(e);
		}
		
		Assert.assertNotEquals(0, list.size());
	}
	@Test
	public void test02InsertEmployee() throws SQLException {
		Employee newEmp = new Employee(1005, "우선미", "사원", new Employee(1003), 1500000, new Department(2));
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test04DeleteEmployee() {
		Employee delEmp = new Employee(1005);
		try {
			int res = dao.deleteEmployee(delEmp);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test03UpdateEmployee() {
		Employee updateEmp = new Employee();
		try {
			int res = dao.updateEmployee(updateEmp);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test05SelectEmployee() {
		Employee selEmp = new Employee(1004);
		try {
			Employee res = dao.selectEmployeeByNo(selEmp);
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}










