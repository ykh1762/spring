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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context.xml")
public class IocMainTest {
	private Logger logger = LoggerFactory.getLogger(IocMainTest.class);
	// junit -> main 메서드 없이도 실행 가능함.
	// junit -> spring framework annotation 추가.
	
	// rangerDao, rangerService
	
	// ** DI(Dependency Injection). annotation을 통한 주입.
	@Resource(name="rangerDaoSpringBean")
	private IRangerDao rangerDao;
	
	// singleton 확인
	@Resource(name="rangerDaoSpringBean")
	private IRangerDao rangerDao2;
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao3;
	
	// prototype scope
	@Resource(name="rangerDaoPrototype")
	private IRangerDao rangerDaoPrototype1;
	
	@Resource(name="rangerDaoPrototype")
	private IRangerDao rangerDaoPrototype2;
	
	
	@Test
	public void testRangerDao() {
		// 기존 방법
		// ApplicationContext context = new ...
		// DL을 통해 스프링 컨테이너에 스프링 빈을 요청.
		// IRangerDao rangerDao = (casting) context.getBean("rangerDao");
		
		// 변경 방법
		// 스프링 컨테이너 생성을 junit runner에게 위임.
		// 우리가 사용하고자 하는 객체를 DI(Dependency Injection)를 통해 주입받는다.
		// @Autowired(스프링), @Resource(java 표준)
		
		// 테스트
		// 1. 스프링 빈이 정상적으로 생성되고, 주입이 문제가 없는지
		assertNotNull(rangerDao);
		
	}
	
	/**
	 * 
	 * Method : testSpringSingletonBean
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : 같은 이름의 스프링 빈(scope = singleton)을 두번 주입받았을 때 해당 객체가 
	 *  동일한 객체인지 비교.
	 */
	@Test
	public void testSpringSingletonBean() {
		/***Given***/
		
		/***When***/
		logger.debug("rangerDao : {}", rangerDao.hashCode());
		logger.debug("rangerDao2 : {}", rangerDao3.hashCode());
		
		/***Then***/
		// 둘이 같은지 비교.
		assertEquals(rangerDao, rangerDao2);
		
	}
	
	/**
	 * 
	 * Method : testSpringBeanSingleton
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : 이름만 다르고 같은 클래스를 등록한 빈(singleton)을 서로다른 객체에 
	 *  주입했을 때 두 객체가 같은지 비교(디자인 패턴의 정의와 일치하는 지).
	 */
	@Test
	public void testSpringBeanSingleton() {
		/***Given***/
		// bean name="rangerDaoSpringBean" / bean name="rangerDao" @ application-context.xml
		
		//@Resource(name="rangerDaoSpringBean")		@Resource(name="rangerDao")
		//private IRangerDao rangerDao;			private IRangerDao rangerDao3;
		
		/***When***/
		logger.debug("rangerDao : {}", rangerDao.hashCode());
		logger.debug("rangerDao3 : {}", rangerDao3.hashCode());

		/***Then***/
		//assertEquals(rangerDao, rangerDao3);

		// failure -> 둘이 다름?
		
		// 디자인 패턴에 의하면 같은 클래스로부터 하나의 인스턴스만 존재하게 하는 패턴이
		// 싱글톤.
		// rangerDaoSpringBean과 rangerDao 스프링 빈은 서로 같은 RangerDaoImpl 클래스로부터
		// 생성된 객체. 디자인 패턴의 정의에 의해서 두개의 객체는 서로 같아야된다.
		
		// 스프링 bean scope에서 이야기하는 singleton은 스프링 이름 단위로 객체가 생성된다.
		// rangerDaoSpringBean과 rangerDao는 같은 클래스로부터 생성되었지만 spring bean
		// 이름을 서로 다르게 선언하였기 때문에 스프링 컨테이너 내에서는 서로 다른 객체로
		// 인식을 한다.
		
		assertNotEquals(rangerDao, rangerDao3);
		
	}
	
	// prototype scope : 스프링 컨테이너로 부터 prototype bean을 요청하면 요청할 때마다 새로운
	// 객체를 생성하여 돌려준다.
	/**
	 * 
	 * Method : testSpringPrototypeBean
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : spring prototype bean 테스트.
	 */
	@Test
	public void testSpringPrototypeBean() {
		/***Given***/
		// rangerDaoPrototype1, 2
		
		/***When***/

		/***Then***/
		assertNotEquals(rangerDaoPrototype1, rangerDaoPrototype2);
		
	}
	
	
	
	
	
	
	
	

}












