package com.yedam.service;

import java.util.List;

import com.yedam.domain.BookVO;

public interface BookService {

	public void register(BookVO book);	// 추가
	public List<BookVO> getList(int bookNo);
}
