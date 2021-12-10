package com.fastcampus.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
	public static void main(String[] args) throws Exception{

		// 1. YoilTeller 클래스의 객체를 생성
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		// 2. 모든 메서드 정보를 가져와서 배열에 저장
		Method[] methodArr = clazz.getDeclaredMethods();
		
		for(Method m : methodArr) {
			String name = m.getName();						//메서드 이름
			Parameter[] paramArr = m.getParameters();		//매개변수 목록
//			Class[] paramTypeArr = m.getParameterTypes();	//메서드 반환타입
			Class returnType = m.getReturnType();
			
			StringJoiner paramList = new StringJoiner(", ", "(", ")");
			
			for(Parameter param : paramArr) {
				String paramName = param.getName();
				Class  paramType = param.getType();
				
				paramList.add(paramType.getName() + " " + paramName);
			}
			
			System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
			//void main(javax.servlet.http.HttpServletRequest arg0, javax.servlet.http.HttpServletResponse arg1)
			// arg0, arg1 이라고 나오는 것은 컴파일시에는 매개변수 이름은 상관없기 때문에 컴파일러가 임의로 지정하기 때문이다.
			// 매개변수 이름을 저장하기 위해서는 "javac -parameters"라는 옵션을 통해 매개변수를 저장할 수 있다.
		}
	} // main
}

/* [실행결과]
java.lang.String main(java.lang.String year, java.lang.String month, java.lang.String day, org.springframework.ui.Model model)
boolean isValid(int year, int month, int day)
*/