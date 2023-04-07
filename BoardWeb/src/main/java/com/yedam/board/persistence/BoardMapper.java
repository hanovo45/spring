package com.yedam.board.persistence;

import java.util.List;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.domain.Criteria;

public interface BoardMapper {
	
	// 기본적인 C(reate), R(ead), U(pdate), D(elete) 처리.

//	@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList(); // 목록.
	public List<BoardVO> getListWithPaging(Criteria cri);
	public void insertSelectKey(BoardVO board);
	public BoardVO read(Long bno);
	public int delete(Long bno);
	public int update(BoardVO board);
	public int getTotalCount(Criteria cri);
}
