package kr.iiac.bugs.web;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImgVO {

	private MultipartFile Filedata;
	private String callback;
	private String callback_func;
	
}
