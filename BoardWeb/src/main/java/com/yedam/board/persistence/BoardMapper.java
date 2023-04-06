package com.yedam.board.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yedam.board.domain.BoardVO;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();	// 목록
	public void insertSelectKey(BoardVO board);	// 추가
	public BoardVO read(Long bno);	// 조회
	public int delete(Long bno);	// 삭제
	public int update(BoardVO board);	// 업데이트
}
