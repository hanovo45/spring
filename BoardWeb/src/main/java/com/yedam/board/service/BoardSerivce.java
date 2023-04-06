package com.yedam.board.service;

import java.util.List;

import com.yedam.board.domain.BoardVO;

public interface BoardSerivce {

	// CRUD 등록
	public void register(BoardVO board);	// 등록
	public BoardVO get(Long bno);	// 한건조회?
	public boolean modify(BoardVO board);	// 수정,업데이트
	public boolean remove(Long bno);	// 삭제
	public List<BoardVO> getList();	// 전체조회
}
