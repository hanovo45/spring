package com.yedam.service;

import java.util.List;

import com.yedam.domain.BookVO;

public interface BookService {

	public int register(BookVO vo);	// 추가
	public List<BookVO> getList(BookVO vo);
}
