package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-collection.xml")
public class CollectionBeanTest {
	private Logger logger = LoggerFactory.getLogger(CollectionBeanTest.class);
	
	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	/**
	 * 
	 * Method : testCollectionBean
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : 스프링 설정 파일을 통해 컬렉션 객체를 생성하고 주입받는다. (list, set, map,
	 *  properties)
	 */
	@Test
	public void testCollectionBean() {
		/***Given***/
		
		/***When***/
		List<String> list = collectionBean.getList();
		
		for(String str : list) {
			logger.debug("list : {}", str);			
		}
		
		// set, map, properties 출력해보기.
		Set<String> set = collectionBean.getSet();
		for(String str : set) {
			logger.debug("set : {}", str);						
		}
		
		Map<String, String> map = collectionBean.getMap();
		logger.debug("map : {}", map);						
		
		Properties properties = collectionBean.getProperties();
		logger.debug("properties : {}", properties);						
		
		
		/***Then***/
		assertNotNull(list);
		assertEquals(3, list.size());
		
		// assert 구문을 이용하여 속성이 정상적으로 주입되었는지 테스트 코드 작성.
		assertNotNull(set);
		assertNotNull(map);
		assertNotNull(properties);
		
	}

	
	
	
}
















