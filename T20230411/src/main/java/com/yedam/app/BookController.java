package com.yedam.app;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.domain.BookVO;
import com.yedam.service.BookService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/book/*")
@Log4j
public class BookController {
	
	@Setter(onMethod_ = @Autowired)
	private BookService bookSerivce;
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(BookVO book, RedirectAttributes model) {
		
		log.info("등록 구현?");
		bookSerivce.register(book);

		return "redirect:/book/list";
	}
	
	@GetMapping("register")
	public String register() {
		
		return "/WEB-INF/views/book/register.jsp";
	}
	
	@RequestMapping("list")
	public String list(BookVO vo) {
		log.info("컨트롤 .. 목록조회?");
		List<BookVO> list = bookSerivce.getList(vo);		
		
		return "/WEB-INF/views/book/list.jsp";
	}
}
