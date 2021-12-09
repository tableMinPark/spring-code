package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1. 원격 호출가능한 프로그램으로 등록 (@Controller)
@Controller
public class Hello {
	int iv = 10;			//인스턴스 변수
	static int cv = 20;		//static 변수
	
	//2. URL과 메서드를 연결 (@RequestMapping)
	
	@RequestMapping("/hello")		
	public void main() {	//인스턴스 메서드 - iv, cv 둘다 사용가능
		//Tomcat console에 출력했기 때문에 Error 404
		System.out.println("Hello");
		System.out.println(cv);		//OK
		System.out.println(iv);		//OK
	}	
	
	@RequestMapping("/hello2")
	public static void main2() {	//static 메서드 - cv만 사용가능
		System.out.println("Hello (static)");
		System.out.println(cv);		//OK
//		System.out.println(iv);	//ERROR
	}
	
	@RequestMapping("/hello3")
	private void main3() {
		System.out.println("Hello (private)");
	}
}