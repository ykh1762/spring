package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
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
		//return "user/userAllList";
		
		// tiles
		return "userAllListTiles";
	}

	/**
	 * 
	 * Method : userPagingList
	 * 작성자 : PC19
	 * 변경이력 :
	 * @param pageVo
	 * @param model
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회.
	 */
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
		
		//return "user/userPagingList";
		
		// tiles
		return "userPagingListTiles";
	}
	
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public String user(@RequestParam("userId") String userId, Model model){
/*		// userId parameter 확인.
		String userId = request.getParameter("userId");
		
		// 해당 파라미터로 userService.selectUser(userId);
		UserVo userVo = userService.selectUser(userId);
		
		// 조회된 user객체를 request객체에 속성으로 저장.
		request.setAttribute("userVo", userVo);
		
		// 사용자 상세 화면을 담당하는 view인 user.jsp로 위임.
		request.getRequestDispatcher("/user/user.jsp").forward(request, response);		
*/
		UserVo userVo = userService.selectUser(userId);
		
		model.addAttribute(userVo);
		
		//return "user/user";
		
		// tiles
		return "userTiles";
	}
	
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletRequest req, HttpServletResponse resp, 
			@RequestParam("userId") String userId) throws IOException{
		resp.setHeader("Content-Disposition", "attachment; filename=profile.png");
		resp.setContentType("image"); 

		UserVo userVo = userService.selectUser(userId);		
		
		FileInputStream fis;
		
		if(userVo != null && userVo.getRealFileName() != null)
			fis = new FileInputStream(new File(userVo.getRealFileName()));
			
		else{
			ServletContext application = req.getServletContext();
			String noimgPath = application.getRealPath("upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));
		}
		
		ServletOutputStream sos = resp.getOutputStream();
		
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fis.read(buff)) > -1){
			sos.write(buff);
		}
		
		sos.close();
		fis.close();		
		
	}
	
	/**
	 * 
	 * Method : userForm
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록폼 요청.
	 */
//	@RequestMapping("/userForm") get방식으로만 처리하도록 변경.
	@RequestMapping(path="/userForm", method=RequestMethod.GET)
	public String userForm(){
		return "user/userForm";
	}
	
	@RequestMapping(path="/userForm", method=RequestMethod.POST)
	public String userForm(UserVo userVo, @RequestPart("profile") MultipartFile profile,
			HttpSession session, Model model) throws IllegalStateException, IOException{
		
		UserVo duplicateUserVo = userService.selectUser(userVo.getUserId());
		
		String filename = "";
		String realFilename = "";
		
		// 중복체크 통과(신규등록)
		if(duplicateUserVo == null){
			// 사용자 프로파일을 업로드 한 경우.
			if(profile.getSize() > 0){
				filename = profile.getOriginalFilename();
				realFilename = "d:\\picture" + UUID.randomUUID().toString();
				
				profile.transferTo(new File(realFilename));
			}
			
			userVo.setFileName(filename);
			userVo.setRealFileName(realFilename);
			
			// 사용자 비밀번호 암호화 로직 추가.
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			

			
			
			int insertCnt = 0;
			
			try{
				insertCnt = userService.insertUser(userVo);				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(insertCnt == 1){
				// 입력 성공.
				session.setAttribute("msg", "정상 등록 되었습니다.");
				
				// * spring redirect 방법
				return "redirect:/user/userPagingList"; // contextPath 작업 필요.
			}else{
				// 입력 실패.
				// doGet() 호출 -> userForm() 호출.
				return "user/userForm"; // 검증 필요.
			}	
		}else{
			// 중복체크 실패.
			model.addAttribute("msg", "중복체크에 실패 했습니다.");
			return "user/userForm"; // 검증 필요.
		}
		
	}
	
	/**
	 * 
	 * Method : userModifyForm
	 * 작성자 : PC19
	 * 변경이력 :
	 * @param userId
	 * @param model
	 * @return
	 * Method 설명 : 정보 수정 폼 요청.
	 */
	@RequestMapping(path="/userModifyForm", method=RequestMethod.GET)
	public String userModifyForm(@RequestParam("userId") String userId, Model model){
		UserVo userVo = userService.selectUser(userId);
		
		model.addAttribute("user", userVo);
		
		return "user/userModify";
	}
	
	@RequestMapping(path="/userModifyForm", method=RequestMethod.POST)
	public String userModifyForm(UserVo userVo, @RequestPart("profile") MultipartFile profilePart,
			HttpSession session, Model model, RedirectAttributes ra,
			HttpServletRequest req) throws IllegalStateException, IOException{
	// UserVo를 인자로 하면 알아서 mapping이 되네.
//	public String userModifyForm(@RequestParam("userId") String userId, @RequestParam("userNm") String userNm, 
//			@RequestParam("alias") String alias, @RequestParam("addr1") String addr1, 
//			@RequestParam("addr2") String addr2, @RequestParam("zipcode") String zipcode, 
//			@RequestParam("pass") String pass, @RequestPart("profile") MultipartFile profilePart,
//			HttpSession session) throws IllegalStateException, IOException{
		
		userVo = new UserVo(userVo.getUserId(), userVo.getUserNm(), userVo.getAlias(), userVo.getAddr1(), 
				userVo.getAddr2(), userVo.getZipcode(), userVo.getPass());
//		UserVo userVo = new UserVo(userId, userNm, alias, addr1, addr2, zipcode, pass);
		
		String fileName = "";
		String realFileName = "";
		
		if(profilePart.getSize() > 0){
			fileName = profilePart.getOriginalFilename();
			realFileName = "d:\\picture" + UUID.randomUUID().toString();
			
			profilePart.transferTo(new File(realFileName));
			
		}
		
		userVo.setFileName(fileName);
		userVo.setRealFileName(realFileName);
		
		// 비밀번호 수정 요청여부
		// 사용자가 값을 입력하지 않은 경우 -> 기존 비밀번호 유지.
		if(userVo.getPass().equals("")){
			UserVo userVoForPass = userService.selectUser(userVo.getUserId());
			userVo.setPass(userVoForPass.getPass());
		}else{
			// 사용자가 비밀번호를 신규 등록한 경우.
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		}
		
		
		int updateCnt = userService.updateUser(userVo);
		
		if(updateCnt == 1){
//			session.setAttribute("updateMsg", "정보 수정 완료.");

			
			// * redirect 파라미터 보내는 방법.
			// 1. url로 작성.
			// "redirect:/user/user?userId=" + userVo.getUserId();
			//
			// 2. model객체의 속성 이용. (저장된 속성을 자동으로 파라미터 변환)
			// model.addAttribute("userId", userVo.getUserId());
			// return "redirect:/user/user";
			//
			// 3. RedirectAttributes(ra) 객체를 이용.
			// ra.addAttribute("userId", userVo.getUserId());
			// return "redirect:/user/user";
			
			// 1.
//			return "redirect:/user/user?userId=" + userVo.getUserId();
			
			// 2.
//			model.addAttribute("userId", userVo.getUserId());
//			return "redirect:/user/user";
			
			// 3. RedirectAttributes(ra) 객체 - FlashAttribute. 잠시동안 존재.
			ra.addAttribute("userId", userVo.getUserId());
			ra.addFlashAttribute("msg", "정상 등록 되었습니다.");
			
			// 이 경우는 redirect를 하면 값이 사라져서 msg가 전달되지 않는다.
			//model.addAttribute("msg", "정상 등록 되었습니다.")
			
			// contextPath가 있는 경우 추가.
			return "redirect:"+ req.getContextPath() +"/user/user";
			
		}else{
			return "user/userModify";
		}

		
	}
	
	
	
	
	
	
	
	
	
}

















