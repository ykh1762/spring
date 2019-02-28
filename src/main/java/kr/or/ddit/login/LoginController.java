package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

@Controller
public class LoginController {

	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * 
	 * Method : loginView
	 * 작성자 : PC19
	 * 변경이력 :
	 * @return
	 * Method 설명 : 로그인 화면 요청을 처리.
	 */
	// method 속성이 설정되어 있지 않으면 모든 method에 대해 처리가 됨.
	@RequestMapping(path={"/login"}, method={RequestMethod.GET})
	public String loginView(){
		// at servlet-context.xml
//		<property name="prefix" value="/WEB-INF/views/"/>
//		<property name="suffix" value=".jsp"/>
		return "login/login";
	}
	
	@RequestMapping(path={"/login"}, method={RequestMethod.POST})
	public String loginProcess(UserVo userVo, HttpSession session){
		// 사용자가 요청한 id에 해당하는 실제 데이터베이스에 저장된 값.
		UserVo dbUserVo = userService.selectUser(userVo.getUserId());
		
		// 정상 로그인 
		if(dbUserVo.getUserId().equals(userVo.getUserId()) &&
				dbUserVo.getPass().equals(KISA_SHA256.encrypt(userVo.getPass()))){
			session.setAttribute("userVo", dbUserVo);
			
			return "main";
		}else{
			// 로그인 실패.
			return "login/login";
		}
		
	}

	
	
	
}















