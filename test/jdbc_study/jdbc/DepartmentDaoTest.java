package jdbc_study.jdbc;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DepartmentDaoImpl;
import jdbc_study.dto.Department;

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
	public void testSelectDepartmentByAll() {
		List<Department> list = dao.selectDepartmentByAll();
		for(Department dept : list) {
			MySQLJdbcUtilTest.LOG.debug(dept);
		}
		
		Assert.assertNotEquals(0, list.size());//괄호안의 두 값이 달라야(not equals) 성공한것
	}

}
