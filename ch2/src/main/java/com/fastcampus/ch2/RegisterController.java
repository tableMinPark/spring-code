package com.fastcampus.ch2;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
//	@RequestMapping(value="/register/add", method={RequestMethod.GET, RequestMethod.POST})
	@GetMapping("/register/add")
	public String register() {
		return "registerForm";	// WEB-INF/views/registerForm.jsp	
	}
// servlet-context.xml 에 view-controller 등록해서 필요없어짐.
	
	@InitBinder		// RegisterController 에서만 사용가능
	public void toDate(WebDataBinder binder) {
//		ConversionService conversionService = binder.getConversionService();
//		System.out.println("conversionService="+conversionService);
		
//		// 자동검증 - UserValidator 를 WebDatabinder에 등록 (아래의 검증할 객체앞에 @valid 를 붙혀줘야함.)
//		binder.setValidator(new UserValidator());
		
//		// 자동검증 (GlobalValidator)
//		binder.addValidators(new UserValidator());
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList="+validatorList);
		
		// 생일
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");					// 변환할 날짜형식 지정
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	// 날짜형식을 binder(WebDataBinder) 에 추가
		//@DateTimeFormat 로 형식지정을 해주면 InitBinder을 등록 할 필요가없다.

		// 취미
		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	// hobby라는 이름을가진 필드의 형식을 지정
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/add")	// 4.3 부터
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {	// BindingResult 는 바인딩할 객체 바로 뒤에 위치해야함. + BindingResult 가 존재하면 ErrorPage로 이동하지 않고 BindingResult객체에 Error에  대한 정보들이 담겨있음
		System.out.println("result="+result);
		System.out.println("user="+user);
		
//		// 수동검증 - Validator를 직접  생성하고, validate()를 직접 호출 (자동호출은 @InitBinder 에 WebDataBinder에 생성한 Validator를 validator로 등록해줘야함)
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result);			//BindingResult는 Errors의 자손
						
		// User 객체를 검증한 결과 registerForm을 이용해서 에러를 보여줘야 함.
		if (result.hasErrors()) {
			return "registerForm";
		}
		
//		// 1. 유효성 검사
//		if (!isValid(user)) {
//			String msg = URLEncoder.encode("ID를 잘못 입력하셨습니다.", "utf-8");
//			
//			m.addAttribute("msg", msg);
//			return "forward:/register/add";
////			return "redirect:/register/add";
//			
////			return "redirect:/register/add?msg="+msg; // URL 재작성 (rewriting)
//		}
		
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
