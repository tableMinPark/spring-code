package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 기존의 YoilTeller의 관심사를 분리한다.

@Controller
public class YoilTellerMVC_ModelAndView {	//http://localhost:8080/ch2/getYoil?year=2021&month=12&day=4
	@RequestMapping("/getYoilMVC_ModelAndView")
	public ModelAndView main(int year, int month, int day) throws IOException {
		ModelAndView mv = new ModelAndView();
		
		// 1. 요일 계산			
		char yoil = getYoil(year, month, day);
		
		// 2. 계산한 요일 결과를 ModelAndView에 저장
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);
		
		// 3. 결과를 보여줄 view를 지정		
		mv.setViewName("getYoilMVC_ModelAndView");		
		return mv;
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일 2:월요일 ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}

}