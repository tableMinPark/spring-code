package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main3();	//private �� �ܺ� ȣ�� �Ұ�
		
		//Reflection API�� ��� - Ŭ���� ������ ��� �ٷ� �� �ִ� ������ �������
		//java.lang.reflect ��Ű���� ����
		
		//HelloŬ������ Class��ü (Ŭ������ ������ ��� �ִ� ��ü)�� ���´�.
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
		Hello hello = (Hello)helloClass.newInstance();
		Method main = helloClass.getDeclaredMethod("main3");
		main.setAccessible(true);	//private�� main()�� ȣ�Ⱑ���ϰ� �Ѵ�.
		
		main.invoke(hello);
	}
}
