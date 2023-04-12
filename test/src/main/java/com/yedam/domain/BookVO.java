package com.yedam.domain;

import java.util.Date;

import lombok.Data;
@Data
public class BookVO {

	private int bookNo;
	private String bookName;
	private String bookCoverimg;
	private Date bookDate;
	private int bookPrice;
	private String bookPublisher;
	private String bookInfo;
}
