package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

//@RunWith ... 대신 extends!
public class UserControllerTest extends WebTestConfig{

	/**
	 * 
	 * Method : testUserAllList
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : 사용자 전체 조회 테스트.
	 * @throws Exception 
	 */
	@Test
	public void testUserAllList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		List<UserVo> userList = (List<UserVo>) mav.getModel().get("userList");
		
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("user/userAllList", viewName);
		assertNotNull(userList);
		
		// 값을 대략 알때. size가 100이 넘는지 판별.
		assertTrue(userList.size() > 100);

	}
	
	@Test
	public void testUserPagingList() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userPagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		// ** modelMap ?
		ModelMap modelMap = mav.getModelMap();
		List<UserVo> userList = (List<UserVo>) mav.getModel().get("userList");
		int userCnt = (int) modelMap.get("userCnt");
		int page = (int) modelMap.get("page");
		int pageSize = (int) modelMap.get("pageSize");
		String viewName = mav.getViewName();

		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());
		assertEquals(1, page);
		assertEquals(10, pageSize);
		assertEquals("user/userPagingList", viewName);
		assertTrue(userCnt > 100);

	}
	
	@Test
	public void testUser() throws Exception{
		/***Given***/
		String userId = "brown";
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", userId)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		UserVo userVo = (UserVo) mav.getModel().get("userVo");

		/***Then***/
		assertNotNull(userVo);

	}

	//@Test
	//public void testProfileImg(){}
	
	

}














