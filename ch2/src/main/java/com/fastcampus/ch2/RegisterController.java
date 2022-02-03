package com.fastcampus.ch2;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	@RequestMapping(value="/register/add", method={RequestMethod.GET, RequestMethod.POST})
	public String register() {
		return "registerForm";	// WEB-INF/views/registerForm.jsp	
	}
// servlet-context.xml 에 view-controller 등록해서 필요없어짐.
	
	@InitBinder		// RegisterController 에서만 사용가능
	public void toDate(WebDataBinder binder) {
//		ConversionService conversionService = binder.getConversionService();
//		System.out.println("conversionService="+conversionService);
		
		// 생일
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");					// 변환할 날짜형식 지정
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	// 날짜형식을 binder(WebDataBinder) 에 추가
		//@DateTimeFormat 로 형식지정을 해주면 InitBinder을 등록 할 필요가없다.

		// 취미
		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	// hobby라는 이름을가진 필드의 형식을 지정
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/save")	// 4.3 부터
	public String save(User user, BindingResult result, Model m) throws Exception {	// BindingResult 는 바인딩할 객체 바로 뒤에 위치해야함. + BindingResult 가 존재하면 ErrorPage로 이동하지 않고 BindingResult객체에 Error에  대한 정보들이 담겨있음
		System.out.println("result="+result);
		System.out.println("user="+user);
		// 1. 유효성 검사
		if (!isValid(user)) {
			String msg = URLEncoder.encode("ID를 잘못 입력하셨습니다.", "utf-8");
			
			m.addAttribute("msg", msg);
			return "forward:/register/add";
//			return "redirect:/register/add";
			
//			return "redirect:/register/add?msg="+msg; // URL 재작성 (rewriting)
		}
		
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
