package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	/**
	 * 
	 * Method : lprodList
	 * 작성자 : PC19
	 * 변경이력 :
	 * @param model
	 * @return
	 * Method 설명 : 제품 그룹리스트 페이지 요청.
	 */
	@RequestMapping("/lprodList")
	public String lprodList(Model model){
		/*
		// lprod 전체 정보를 조회.
		List<LprodVo> lprodList = lprodService.getAllLprod();
		
		// 전체 정보를 request 객체에 속성으로 설정.
		request.setAttribute("lprodList", lprodList);
		
		// webapp/lprod/lprodList.jsp로 forward.
		request.getRequestDispatcher("/lprod/lprodList.jsp").forward(request, response);
*/
		List<LprodVo> lprodList = lprodService.getAllLprod();
		model.addAttribute("lprodList", lprodList);
		
		return "lprod/lprodList";
	}
	
	/**
	 * 
	 * Method : lprodPagingList
	 * 작성자 : PC19
	 * 변경이력 :
	 * @param pageVo
	 * @param model
	 * @return
	 * Method 설명 : 제품 페이징 그룹리스트 요청.
	 */
	@RequestMapping("/lprodPagingList")
	public String lprodPagingList(PageVo pageVo, Model model){
		/*
		// page, pageSize에 해당하는 파라미터를 받기. --> pageVo. 단, 파라미터가 null일 경우
		// --> page : 1, pageSize : 10.
		int page = request.getParameter("page") == null ? 
				1 : Integer.parseInt(request.getParameter("page"));
		
		int pageSize = request.getParameter("pageSize") == null ? 
				5 : Integer.parseInt(request.getParameter("pageSize"));
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		// userService 객체를 이용해서 userPagingList 조회.
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVo);
		
		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		int lprodCnt = (Integer) resultMap.get("lprodCnt");
		
		// request 객체에 조회된 결과를 속성으로 설정.
		request.setAttribute("lprodList", lprodList);
		request.setAttribute("lprodCnt", lprodCnt);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("page", page);
		
		// userPagingList를 화면으로 출력할 jsp로 위임(forward).
		request.getRequestDispatcher("/lprod/lprodPagingList.jsp").forward(request, response);
		 */
		
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVo);
		
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		
		return "lprod/lprodPagingList";		
	}
	
	/**
	 * 
	 * Method : prodList
	 * 작성자 : PC19
	 * 변경이력 :
	 * @param lprod_gu
	 * @param model
	 * @return
	 * Method 설명 : 제품 리스트 요청.
	 */
	@RequestMapping("/prodList")
	public String prodList(@RequestParam("lprod_gu") String lprod_gu, Model model){
		/*
		// parameter 확인.
		String lprod_gu = request.getParameter("lprod_gu");
		System.out.println(lprod_gu);
		
		// 해당 파라미터로 userService.selectUser(userId);
		List<ProdVo> prodList = lprodService.selectLprod(lprod_gu);
		
		// 조회된 user객체를 request객체에 속성으로 저장.
		request.setAttribute("prodList", prodList);
		request.setAttribute("lprod_gu", lprod_gu);
		
		// 사용자 상세 화면을 담당하는 view인 user.jsp로 위임.
		request.getRequestDispatcher("/lprod/prodList.jsp").forward(request, response);
		 */
		List<ProdVo> prodList = lprodService.selectLprod(lprod_gu);
		model.addAttribute("prodList", prodList);
		model.addAttribute("lprod_gu", lprod_gu);
		
		return "lprod/prodList";
	}
	
	
	
	

}
















