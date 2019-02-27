package kr.or.ddit.ranger.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ranger.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;

@RequestMapping("/ranger")
// @Controller에서 에디터 에러. -> STS 3.8.4로 버전 변경.
@Controller
public class RangerController {
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	/**
	 * 
	 * Method : getRangers
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 레인저스 리스트를 조회.
	 */
	// localhost/ranger/getRangers 라고 요청시 밑의 메소드에서 요청을 처리
	@RequestMapping("/getRangers")
	public String getRangers(Model model) {
		List<String> rangers = rangerService.getRangers();
		
		// 기존 : request.setAttribute("rangers", rangers);
		model.addAttribute("rangers", rangers);
		
		// servlet-context.xml -> prefix, suffix -> /WEB-INF/views/ + ranger/rangers + .jsp
		return "ranger/rangerList";
	}
	
	// localhost/ranger/getRanger?listIndex=2 요청시 아래 메서드에서 요청 처리.
//	@RequestMapping("/getRanger")
//	public String getRanger(HttpServletRequest req, Model model) {
//		int listIndex = Integer.valueOf(req.getParameter("listIndex"));
//		
//		String ranger = rangerService.getRanger(listIndex);
//		
//		model.addAttribute("ranger", ranger);
//		
//		return "ranger/ranger";
//	}
	
	// localhost/ranger/getRanger?listIndex=2 요청시 아래 메서드에서 요청 처리.
	// vo객체에 파라미터 명과 동일한 이름 필드가 존재하면 파라미터를 해당 필드에 바인딩 시켜준다.
	@RequestMapping("/getRanger")
	public String getRanger(RangerVo rangerVo, Model model) {
		String ranger = rangerService.getRanger(rangerVo.getListIndex());
		
		model.addAttribute("ranger", ranger);
		
		return "ranger/ranger";
	}
	
	
}














