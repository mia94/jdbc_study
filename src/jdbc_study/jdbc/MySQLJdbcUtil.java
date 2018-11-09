package jdbc_study.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLJdbcUtil {
	//테스트용 메인, 우리는 다른곳에서 테스트할 것
	/*public static void main(String[] args) {
		try {
			Connection con = MySQLJdbcUtil.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			System.out.println(e.getMessage());//에러메세지를 띄워줌
			e.printStackTrace();
		}
	}*/
	//db.properties에 있는 정보로 연결하기
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try(InputStream is = ClassLoader.getSystemResourceAsStream("db.properties")){
			Properties properties = new Properties();
			properties.load(is);
			
//			System.out.println(properties.getProperty("user"));//키(user)를 넣으면 값(user_wsm)이 출력됨
//			System.out.println(properties.getProperty("password"));
//			System.out.println(properties.getProperty("driver"));
//			System.out.println(properties.getProperty("url"));
			
			conn = DriverManager.getConnection(properties.getProperty("url"), properties);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;//연결식별자
	}
}
