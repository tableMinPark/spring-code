package com.fastcampus.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("GlobalValidator.validate() is called");
		
		User user = (User)target;
		
		String id = user.getId();
		
//		if (id == null || "".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}		// ValidationUtils 를 이용하여 코드 간소화
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		
		if (id == null || id.length() < 5 || id.length() > 12) {
			errors.rejectValue("id", "invalidLength", new String[] {"5", "12"}, null);
		}
	}

}
