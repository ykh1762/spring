package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

// 설정 정보.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-annotation.xml")
public class AnnotationScanTest {
	private Logger logger = LoggerFactory.getLogger(AnnotationScanTest.class);

	// rangerDao 주입
	// * name -> resource annotation 붙인 클래스의 이름을 첫 자 소문자로.
	@Resource(name="rangerDaoImpl")
	private IRangerDao rangerDao;
	
	// rangerService 주입
	@Resource(name="rangerServiceImpl")
	private IRangerService rangerService;
	
	// 두 개의 스프링 빈이 정상적으로 생성되었는데 테스트.
	
	/**
	 * 
	 * Method : testRangerDaoBean
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : rangerDao bean 컴포넌트 스캔 테스트.
	 */
	@Test
	public void testRangerDaoBean() {
		/***Given***/
		
		/***When***/
		logger.debug("rangers(dao) : {}", rangerDao.getRangers());
		
		/***Then***/
		assertNotNull(rangerDao);
	}

	/**
	 * 
	 * Method : testRangerServiceBean
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : rangerService bean 컴포넌트 스캔 테스트.
	 */
	@Test
	public void testRangerServiceBean() {
		/***Given***/
		
		/***When***/
		logger.debug("rangers(service) : {}", rangerService.getRangers());
		
		/***Then***/
		assertNotNull(rangerService);
		
	}
	
}













