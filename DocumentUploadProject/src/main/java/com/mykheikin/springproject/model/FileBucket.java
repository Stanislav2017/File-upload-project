package com.mykheikin.springproject.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class FileBucket {

	@Getter
	@Setter
	private MultipartFile file;

	@Getter
	@Setter
	private String description;
}