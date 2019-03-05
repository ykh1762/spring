package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

//@RunWith ... 대신 extends!
public class UserControllerTest extends WebTestConfig{

	private static final String USER_INSERT_TEST_ID = "sallyTest2";
	@Resource(name="userService")
	private IUserService userService;
	
	@Before
	public void initData(){
		userService.deleteUser(USER_INSERT_TEST_ID);
	}
	
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
		
//		List<UserVo> userList = (List<UserVo>) mav.getModel().get("userList");
		List<UserVo> userList = (List<UserVo>) modelMap.get("userList");
		
		int userCnt = (int) modelMap.get("userCnt");
		
		// page 정보는 어디서 넣은거지?
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
	
	@Test
	public void testUserForm() throws Exception{
		/***Given***/
		
		/***When***/
		// template -> "moc"
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("user/userForm", viewName);

	}
	
	/**
	 * 
	 * Method : testUserForm_post
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : 사용자 등록 요청 테스트.
	 */
	@Test
	public void testUserForm_post_success() throws Exception{
		/***Given***/
		File profileFile = new File("C:\\Users\\PC19\\Desktop\\드라이브\\그림 & 음악\\moon.png");
		
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file).param("userId", USER_INSERT_TEST_ID).param("userNm", "샐리테스트")
				.param("alias", "병아리").param("addr1", "대전시 중구 대흥로 76").param("addr2", "2층 ddit")
				.param("zipcode", "39999").param("pass", "testpass"))
				.andExpect(view().name("redirect:/user/userPagingList")).andReturn();
		
		/***When***/
		HttpSession session = mvcResult.getRequest().getSession();

		/***Then***/
		assertEquals("정상 등록 되었습니다.", session.getAttribute("msg"));

	}
	
	/**
	 * 
	 * Method : testUserForm_post_fail_duplicateUser
	 * 작성자 : PC19
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록요청 실패(중복 사용자) 케이스 테스트.
	 */
	@Test
	public void testUserForm_post_fail_duplicateUser() throws Exception{
		/***Given***/
		File profileFile = new File("C:\\Users\\PC19\\Desktop\\드라이브\\그림 & 음악\\moon.png");
		
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file).param("userId", "sally").param("userNm", "샐리테스트")
				.param("alias", "병아리").param("addr1", "대전시 중구 대흥로 76").param("addr2", "2층 ddit")
				.param("zipcode", "39999").param("pass", "testpass"))
				.andExpect(view().name("user/userForm")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String msg = (String) mav.getModel().get("msg");
		
		/***Then***/
		assertEquals("중복체크에 실패 했습니다.", msg);
		
	}
	
	/**
	 * 
	 * Method : testUserForm_post_fail_insertError
	 * 작성자 : PC19
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록 (zipcode 사이즈 sql에러) 테스트.
	 */
	@Test
	public void testUserForm_post_fail_insertError() throws Exception{
		/***Given***/
		File profileFile = new File("C:\\Users\\PC19\\Desktop\\드라이브\\그림 & 음악\\moon.png");
		
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file).param("userId", USER_INSERT_TEST_ID).param("userNm", "샐리테스트")
				.param("alias", "병아리").param("addr1", "대전시 중구 대흥로 76").param("addr2", "2층 ddit")
				.param("zipcode", "399990000000").param("pass", "testpass"))
				.andExpect(view().name("user/userForm")).andReturn();
		
		/***When***/

		/***Then***/

	}	
	
	/**
	 * 
	 * Method : testUserModifyForm_get
	 * 작성자 : PC19
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 정보 수정 폼 요청 테스트.
	 */
	@Test
	public void testUserModifyForm_get() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userModifyForm").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("user/userModify", viewName);

	}
	
	/**
	 * 
	 * Method : testUserModifyForm_post
	 * 작성자 : PC19
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 정보 수정 테스트.
	 */
	@Test
	public void testUserModifyForm_post() throws Exception{
		/***Given***/
		
		/***When***/
		File profileFile = new File("C:\\Users\\PC19\\Desktop\\드라이브\\그림 & 음악\\sally.png");
		
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "moon.png", "image/png", fis);
		
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userModifyForm").file(file).param("userId", "brown").param("userNm", "브라운")
				.param("alias", "곰").param("addr1", "대전시 중구 대흥로 76").param("addr2", "2층 ddit")
				.param("zipcode", "11111").param("pass", "testpass"))
				.andExpect(view().name("redirect:/user/user?userId=brown")).andReturn();
		
		/***Then***/

	}
	

	
	
}














