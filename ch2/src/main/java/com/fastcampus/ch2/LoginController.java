package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
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
	
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response) throws Exception {
		System.out.println("id : " + id);
		System.out.println("pwd : " + pwd);
		System.out.println("rememberId : " + rememberId);	// checkbox의 타입을 boolean, String(on/ off) 두가지로 사용할 수 있다.
		
		// 1. id와 pwd를 확인
		if (!loginCheck(id, pwd)) {
			String msg = URLEncoder.encode("id 또는 pwd가 일지하지 않습니다.", "utf-8");
		// 2-1. id와 pwd가 일치하지 않으면, loginForm으로 이동 (redirect)
			return "redirect:/login/login?msg="+msg;
		}
		// 2-2. id와 pwd가 일치하면, 홈으로이동
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
		return "redirect:/";
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}
