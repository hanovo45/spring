package com.yedam.board.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.board.domain.Criteria;
import com.yedam.board.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTest {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper replyMapper;
	
	@Test
	public void listTest() {
		Criteria cri = new Criteria();
		//cri.setPageNum(2);
		
		List<ReplyVO> list = replyMapper.getListWithPaging(300L, cri);
		for(ReplyVO vo : list) {
			log.info(vo);
		}
	}
//	public void deleteTest() {
//		
//	}
	
	public void updateTest() {
		ReplyVO vo = new ReplyVO();
		vo.setRno(655535L);
		vo.setReply("655535번의 새로운 댓글");
		replyMapper.update(vo);
	}
	
	public void insertTest() {
		ReplyVO vo = new ReplyVO();
		vo.setReply("댓글등록1");
		vo.setReplyer("user04");
		vo.setBno(655535L);
		
		if(replyMapper.insert(vo) == 1) {
			log.info("성공");
		}else {
			log.info("실패");
		}
	}
}
