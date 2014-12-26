package eting.domain;

import lombok.Data;

import java.util.regex.Pattern;

@Data
public class StoryType {

	private String typeName;
	private String type;	//1, 2, 3, 4, A, B, C, ...

	private String word;
	private String regExp;
	private String block;

	// 정규식패턴
	private Pattern p;

}
