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
public class BookController {
	
	@Setter(onMethod_ = @Autowired)
	private BookService bookSerivce;
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(BookVO book, RedirectAttributes model) {
		System.out.println("추가1");
		
		bookSerivce.register(book);
		System.out.println("===========");
		System.out.println(book);
		model.addAttribute("book", book);	
		
		return "redirect:/book/register";
	}
	
	@GetMapping("register")
	public String register() {
		
		return "/book/register";
	}
	
	@RequestMapping({"list", "book/list"})
	public String list(BookVO vo) {
		List<BookVO> list = bookSerivce.getList(vo);		
		System.out.println("vo");
		System.out.println(vo);
		return "/book/list";
	}
}
