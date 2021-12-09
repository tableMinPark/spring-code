package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 기존의 YoilTeller의 관심사를 분리한다.

@Controller
public class YoilTellerMVC_AutoMapping {	//http://localhost:8080/ch2/getYoil?year=2021&month=12&day=4
	@RequestMapping("/getYoilMVC_AutoMapping")
	//JSP 파일을 지정하지 않으면 Mapping된 주소(getYoilMVC)로 된 JSP파일로 자동매핑된다. (getYoilMVC.jsp파일을 찾아서 자동으로 매핑)
	public void main(int year, int month, int day, Model model) throws IOException {
		// 1. 요일 계산			
		char yoil = getYoil(year, month, day);
		
		// 2. 계산한 요일 결과를 model에 저장
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일 2:월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}