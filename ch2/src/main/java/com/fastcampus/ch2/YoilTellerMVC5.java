package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YoilTellerMVC5 {
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, BindingResult result) {
		ex.printStackTrace();
		return "getYoilError";
	}
	
	
	@RequestMapping("/getYoilMVC5")
//	public String main(@ModelAttribute("myDate") MyDate date, Model model) throws IOException {	//아래와 동일
	public String main(@ModelAttribute MyDate date, Model model) throws IOException {	
	// ModelAttribute의 "myDate" (key값) 을 넣지 않으면 default로 "MyDate" 의 첫자리가 소문자로 변환된 "myDate" 가 key값이 된다.
	// ModelAttribute 에너테이션은 참조형 매개변수 앞에 붙힐 수 있는데, 생략이 가능하다.
	// 컨트롤러 매개변수 타입이 기본형(String) 일 때에는 RequestParam이 생략된 것이고, 참조형일 때에는 ModelAttribute가 생략된 것이다.

		if (!isValid(date)) 
			return "getYoilError";
		
// 		char yoil = getYoil(date);
		
//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);
		
		return "yoil";
	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}


	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}


	private boolean isValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " 일월화수목금토".charAt(dayOfWeek);
	}
}

//