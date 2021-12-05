package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1. ���� ȣ�Ⱑ���� ���α׷����� ��� (@Controller)
@Controller
public class Hello {
	int iv = 10;			//�ν��Ͻ� ����
	static int cv = 20;		//static ����
	
	//2. URL�� �޼��带 ���� (@RequestMapping)
	
	@RequestMapping("/hello")		
	public void main() {	//�ν��Ͻ� �޼��� - iv, cv �Ѵ� ��밡��
		//Tomcat console�� ����߱� ������ Error 404
		System.out.println("Hello");
		System.out.println(cv);		//OK
		System.out.println(iv);		//OK
	}	
	
	@RequestMapping("/hello2")
	public static void main2() {	//static �޼��� - cv�� ��밡��
		System.out.println("Hello (static)");
		System.out.println(cv);		//OK
//		System.out.println(iv);	//ERROR
	}
	
	@RequestMapping("/hello3")
	private void main3() {
		System.out.println("Hello (private)");
	}
}


//static�� �����ʾƵ� ȣ��Ǵ����� > Tomcat���ο��� ��ü�� �����ϰ� �޼��带 ȣ���ϱ� ����
