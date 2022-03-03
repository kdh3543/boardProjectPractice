package kdh.personal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kdh.personal.dto.MemberDTO;
import kdh.personal.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	@Autowired
	private HttpSession session;
	@Autowired
	private MemberService mService;
	
	@RequestMapping("login")
	public String login(String id, String pw, Model model) {
		int result = mService.login(id, pw);
		System.out.println("result값은: "+result);
		if(result > 0) {
			session.setAttribute("loginId", id);
			model.addAttribute("loginId",id);
		}
		return "index";
	}
	
	@RequestMapping("signUp")
	public String signUp() {
		return "/member/signUp";
	}
	
	@RequestMapping("signIn")
	public String signIn(MemberDTO dto) {
		int result = mService.signUp(dto);
		if(result > 0) {
			return "index";
		}else {
			return "error";
		}
		
	}
	
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	@RequestMapping("mainPage")
	public String mainPage(Model model) {
		String id = (String)session.getAttribute("loginId");
		model.addAttribute("loginId",id);
		return "index";
	}
}
