package jdbc_study.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class MySQLJdbcUtilTest {
//로그찍어보기
	static final Logger LOG = LogManager.getLogger();
	@Test
	public void test() {
		try {
			Connection conn = MySQLJdbcUtil.getConnection();
			LOG.trace(String.format("Connected to DataBase %s successfully.", conn.getCatalog()));
			Assert.assertNotNull(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
