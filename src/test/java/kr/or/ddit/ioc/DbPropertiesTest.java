package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// ** spring framework 사용하기 위한 annotation.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-placeholder.xml")
public class DbPropertiesTest {
	private Logger logger = LoggerFactory.getLogger(DbPropertiesTest.class);

	@Resource(name="dbProperties")
	private DbProperties dbProperties;
	
	/**
	 * 
	 * Method : testPlaceholder
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : property placeholder 테스트.
	 */
	@Test
	public void testPlaceholder() {
		/***Given***/
		
		/***When***/
		String url = dbProperties.getUrl();
		String username = dbProperties.getUsername();
		String password = dbProperties.getPassword();
		String driverClassName = dbProperties.getDriverClassName();
		
		logger.debug("url : {}", url);
		logger.debug("username : {}", username);
		logger.debug("password : {}", password);
		logger.debug("driverClassName : {}", driverClassName);
		
		/***Then***/
		assertNotNull(url);
		assertNotNull(username);
		assertNotNull(password);
		assertNotNull(driverClassName);
		
		assertEquals("jdbc:oracle:thin:@localhost:1522:orcl", url);
		
	}

}
