package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 기존의 YoilTeller의 관심사를 분리한다.

@Controller
public class YoilTellerMVC4 {	//http://localhost:8080/ch2/getYoil?year=2021&month=12&day=4
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "getYoilError";
	}
	
	
	@RequestMapping("/getYoilMVC4")
//  HttpServletRequest를 사용하여 받아오지않고 개별적으로 year, month, day를 받을 수 있다.(String -> int 자동 형변환 된다.)
//  JSP를 통해 출력하기 때문에 HttpServletResponse를 사용하지 않고 Model을 사용하여 값을 JSP로 전달한다.
	public String main(MyDate date, Model model) throws IOException {	
		// 1. 유효성 검사
		if (!isValid(date)) 
			return "getYoilError";
		
		// 2. 요일 계산			
		char yoil = getYoil(date);
		
		// 3. 계산한 요일 결과를 model에 저장
		model.addAttribute("myDate", date);
		model.addAttribute("yoil", yoil);
		
		// 4. 출력으로 값 전달
		return "yoil";		// WEB-INF/views/yoil.jsp 를 사용해서 Model값을 출력	
	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}


	private char getYoil(MyDate date) {
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}


	private boolean isValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일 2:월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}