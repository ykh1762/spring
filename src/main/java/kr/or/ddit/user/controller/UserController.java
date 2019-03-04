package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/user")
@Controller
public class UserController {
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * 
	 * Method : userAllList
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 전체 리스트 조회.
	 */
	@RequestMapping("/userAllList")
	public String userAllList(Model model){
		// 기존
		// userService 사용자 전체 정보를 조회.
//		List<UserVo> userList = userService.getAllUser();
		
		// 사용 전체 정보를 request 객체에 속성으로 설정.
//		request.setAttribute("userList", userList);
		
		// webapp/user/userList.jsp로 forward.
//		request.getRequestDispatcher("/user/userAllList.jsp").forward(request, response);
		
		List<UserVo> userList = userService.getAllUser();
		
		model.addAttribute("userList", userList);
		
		// controller만 다시 만들고 나머지는 재사용(userAllList.jsp)
		return "user/userAllList";
	}

	@RequestMapping("/userPagingList")
	public String userPagingList(PageVo pageVo, Model model){
		
/*		// page, pageSize에 해당하는 파라미터를 받기. --> pageVo. 단, 파라미터가 null일 경우
		// --> page : 1, pageSize : 10.
		int page = request.getParameter("page") == null ? 
				1 : Integer.parseInt(request.getParameter("page"));
		
		int pageSize = request.getParameter("pageSize") == null ? 
				10 : Integer.parseInt(request.getParameter("pageSize"));
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		// userService 객체를 이용해서 userPagingList 조회.
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int userCnt = (Integer) resultMap.get("userCnt");
		
		// request 객체에 조회된 결과를 속성으로 설정.
		request.setAttribute("userList", userList);
		request.setAttribute("userCnt", userCnt);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("page", page);
		
		// userPagingList를 화면으로 출력할 jsp로 위임(forward).
		request.getRequestDispatcher("/user/userPagingList.jsp").forward(request, response);		
*/		
//		PageVo pageVo = new PageVo(page, pageSize);
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		
		return "user/userPagingList";
	}
	
	
}

















