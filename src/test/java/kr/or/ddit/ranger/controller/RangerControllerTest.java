package kr.or.ddit.ranger.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;

/*
 * 1. 스프링 컨테이너 설정 필요
 *  테스트 대상은 RangerController
 *  RangerController는 servlet-context.xml component scan 설정이 되어있음.
 *  RangerController는 RangerService 객체를 주입받음.
 *  RangerService는 RangerDao 객체를 주입받음.
 *  
 *  ** RangerController를 만들기 위해서는 RangerService, RangerDao 스프링 빈이 필요.
 *  그렇기 때문에 RangerController를 스캔하는 servlet-context.xml 뿐만 아니라 RangerService,
 *  RangerDao를 스캔하는 application-context.xml도 필요하다. 
 */

public class RangerControllerTest extends WebTestConfig{
	
	/**
	 * 
	 * Method : testGetRangers
	 * 작성자 : PC19
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 전체 레인저스 조회 테스트.
	 */
	@Test
	public void testGetRangers() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/ranger/getRangers")).andReturn(); // or post("")
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		Map<String, Object> model = mav.getModel();
		List<String> rangers = (List<String>) model.get("rangers");
		List<String> boardGbList = (List<String>) model.get("boardGb");

		/***Then***/
		assertEquals("ranger/rangerList", viewName);
		
		assertNotNull(rangers);
		assertEquals(5, rangers.size());
		
		assertNotNull(boardGbList);
		assertEquals(5, boardGbList.size());
		
	}
	
	/**
	 * 
	 * Method : testGetRangersMav
	 * 작성자 : PC19
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : ModelAndView 리턴 테스트.
	 */
	@Test
	public void testGetRangersMav() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/ranger/getRangersMav")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("ranger/rangerList", mav.getViewName());
		assertEquals(5, ((List<String>) mav.getModel().get("rangers")).size());

	}	
	
	
	/**
	 * 
	 * Method : testGetRanger
	 * 작성자 : PC19
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : listIndex에 해당하는 레인저 이름 조회.
	 */
	@Test
	public void testGetRanger() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/ranger/getRanger").param("listIndex", "2"))
				.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		ModelMap modelMap = mav.getModelMap();
		String ranger = (String) modelMap.get("ranger");
		List<String> boardGbList = (List<String>) modelMap.get("boardGb");
		
		/***Then***/
		assertEquals("ranger/ranger", viewName);
		assertEquals("sally", ranger);
		
		assertNotNull(boardGbList);
		assertEquals(5, boardGbList.size());
		
	}
	



	
}














