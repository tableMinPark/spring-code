package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 1. 세션을 종료
		session.invalidate();
		// 2. 홈으로 이동
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, String toURL, 
			HttpServletRequest request , HttpServletResponse response,
			@CookieValue("id") String cookieId) throws Exception {

		// 1. id와 pwd를 확인
		if (!loginCheck(id, pwd)) {
			String msg = URLEncoder.encode("id 또는 pwd가 일지하지 않습니다.", "utf-8");
		// 2-1. id와 pwd가 일치하지 않으면, loginForm으로 이동 (redirect)
			return "redirect:/login/login?msg="+msg;
		}
		// 2-2. id와 pwd가 일치하면, 세션 객체에 id를 저장
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		if (rememberId) {
			// 3-1. 쿠키를 생성
			Cookie cookie = new Cookie("id", id);
			// 3-3. 응답에 저장
			response.addCookie(cookie);
		}
		else {
			// 3-2. 쿠키를 삭제
			Cookie cookie = new Cookie("id", "");
			cookie.setMaxAge(0);
			// 3-3. 응답에 저장
			response.addCookie(cookie);
		}		
		
		// 3-3. 홈으로 이동
		toURL = toURL == null || toURL.equals("") ? "/" : toURL;
		
		return "redirect:"+toURL;
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}
