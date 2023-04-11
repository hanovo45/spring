package com.yedam.persistence;

import java.util.List;

import com.yedam.domain.BookVO;

public interface BookMapper {

	public List<BookVO> getList();
	public int insert(BookVO vo);
}
