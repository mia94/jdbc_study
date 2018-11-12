package jdbc_study.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.UserDetailDao;
import jdbc_study.dao.UserDetailDaoImpl;
import jdbc_study.dto.Gender;
import jdbc_study.dto.UserDetail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDetailDaoImplTest {
	static UserDetailDao dao;
	
	@BeforeClass 
	public static void setUpBeforeClass() throws Exception { 
		System.out.println(); 
		LogUtil.prnLog("Start UserDetailDaoTest"); 
		dao = new UserDetailDaoImpl(); 
	}
	@AfterClass 
	public static void tearDownAfterClass() throws Exception { 
		System.out.println(); 
		LogUtil.prnLog("End UserDetailDaoTest"); 
		dao = null; 
	}
	
	@Before
	public void setUp() throws Exception {
		System.out.println();
	}
	
	@Test
	public  void test1InsertUserDetail() throws SQLException {
		LogUtil.prnLog("Department fail");
		String bio = "4월 영화 나를 기억해가 개봉했으며 5월 MBC 4부작 … 걱정이 크다";
		UserDetail userDetail = new UserDetail(1, "이유영", Gender.FEMALE, getImage(), bio); 
		int result = dao.insertDetail(userDetail); 
		LogUtil.prnLog(String.format("result %d", result)); 
		Assert.assertNotEquals(2, result);
		
	}
	private byte[] getImage() {
		byte[] pic = null; 
		String imgPath = System.getProperty("user.dir")+ 
						 System.getProperty("file.separator")+ "images" + 
						 System.getProperty("file.separator")+"seohyunjin.jpg"; 
		File file = new File(imgPath); 
		try (InputStream is = new FileInputStream(file)){ 
			pic = new byte[is.available()]; 
			is.read(pic); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}
	
	@Test
	public void test2GetPictureByUserDetail() throws FileNotFoundException, SQLException, IOException { 
		UserDetail userDetail = new UserDetail(1);
		String filePath = System.getProperty("user.dir")+"\\backup\\"; 
		File f = new File(filePath); 
		if (!f.exists()) f.mkdir();
		Random rnd = new Random();
		String realPath = dao.getPictureByUserDetail(userDetail, filePath+ "user"+ rnd.nextInt(10) +".jpg");
		File selImg = new File(realPath); 
		Assert.assertEquals(selImg.exists(), true);
	}

}
