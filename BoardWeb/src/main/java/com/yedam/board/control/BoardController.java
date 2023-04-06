package com.yedam.board.control;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.board.domain.BoardVO;
import com.yedam.board.service.BoardSerivce;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {

	@Setter(onMethod_ = @Autowired)
	private BoardSerivce boardService;
	
	@RequestMapping("list")
	public void list(Model model) {	// 전체목록
	
		log.info("컨트롤.. 목록조회");
		List<BoardVO> list = boardService.getList();
		model.addAttribute("list", list);
		// /WEB-INF/views/board/list.jsp;
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(BoardVO board, RedirectAttributes model) {	// 추가
		
		log.info("컨트롤.. 등록");

		// 등록 처리후 목록이동	
		boardService.register(board);		
		model.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";		// response.sendRedirect();
	}
	
	@GetMapping("register")
	public void register() {
		// 등록화면
	}
	
	@GetMapping("get")
	public void get(Long bno, Model model) {	// 단건조회
	
		log.info("get");
		model.addAttribute("board",boardService.get(bno));
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board, Model model) {	//수정
		
		log.info("컨트롤... 수정");
		
		if(boardService.modify(board)) {
			model.addAttribute("result","Success");
		}
		
		return "redirect:/board/list";		// response.sendRedirect();
	}
	
	@PostMapping("remove")
	public String removo(Long bno, Model model) {	// 삭제
		
		if(boardService.remove(bno)) {
			model.addAttribute("result","SUccess");
		}
		return "redirect:/board/list";		// response.sendRedirect();
	}
	
}
