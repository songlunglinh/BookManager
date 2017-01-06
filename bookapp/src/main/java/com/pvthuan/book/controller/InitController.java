package com.pvthuan.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pvthuan.book.constant.BookInit;
import com.pvthuan.book.model.Book;
import com.pvthuan.book.service.IBookService;

@Controller
@RequestMapping(value = "/book/init")
public class InitController {
	@Autowired
	IBookService bookService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String start(){
		
		for(int i = 0;i<4;i++){
			Book book = new Book();
			book.setAuthor(BookInit.author[i]);
			book.setName(BookInit.book[i]);
			bookService.create(book);
		}
		return "ok";
	}
}
