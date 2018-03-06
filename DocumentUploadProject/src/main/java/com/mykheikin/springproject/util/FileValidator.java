package com.mykheikin.springproject.util;

import com.mykheikin.springproject.model.FileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FileBucket.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		FileBucket file = (FileBucket) obj;

		if(file.getFile() != null){
			if (file.getFile().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}

		if (null == file.getDescription()) {
			errors.rejectValue("description", null, "Description is empty");
		}
	}
}

