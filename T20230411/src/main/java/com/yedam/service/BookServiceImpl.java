package com.yedam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.domain.BookVO;
import com.yedam.persistence.BookMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BookServiceImpl implements BookService{

	@Setter(onMethod_ = @Autowired)
	private BookMapper bookmapper;
	
	@Override
	public void register(BookVO book) {
		log.info("등록 구현");
		bookmapper.insert(book);
	}


	@Override
	public List<BookVO> getList(BookVO vo) {
		log.info("조회 구현");
		return bookmapper.getList();

	}

}
