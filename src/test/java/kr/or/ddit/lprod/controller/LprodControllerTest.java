package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.WebTestConfig;

public class LprodControllerTest extends WebTestConfig{

	/**
	 * 
	 * Method : testLprodList
	 * 작성자 : PC19
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 제품 그룹리스트 요청 테스트.
	 */
	@Test
	public void testLprodList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/lprodList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		List<LprodVo> lprodList = (List<LprodVo>) mav.getModel().get("lprodList");
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("lprod/lprodList", viewName);
		assertEquals(9, lprodList.size());

	}
	
	/**
	 * 
	 * Method : testLprodPagingList
	 * 작성자 : PC19
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 제품 페이징 그룹리스트 요청 테스트.
	 */
	@Test
	public void testLprodPagingList() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/lprodPagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		// 페이징 리스트 메서드 테스트하기.
		ModelMap modelMap = mav.getModelMap();
		
		List<LprodVo> lprodList = (List<LprodVo>) modelMap.get("lprodList");
		int lprodCnt = (int) modelMap.get("lprodCnt");
		int page = (int) modelMap.get("page");
		
		// getModel()이나 getModelMap()이나 거의 비슷. 
		page = (int) mav.getModel().get("page"); 
		int pageSize = (int) modelMap.get("pageSize");
		
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals(9, lprodCnt);
		assertEquals(1, page);
		assertEquals(10, pageSize);
		assertEquals("lprod/lprodPagingList", viewName);

	}
	
	/**
	 * 
	 * Method : testProdList
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : 제품 리스트 요청 테스트.
	 * @throws Exception 
	 */
	@Test
	public void testProdList() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/prodList").param("lprod_gu", "P101")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		List<ProdVo> prodList = (List<ProdVo>) mav.getModel().get("prodList");
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals(6, prodList.size());
		assertEquals("lprod/prodList", viewName);
		
	}
	
	

}











