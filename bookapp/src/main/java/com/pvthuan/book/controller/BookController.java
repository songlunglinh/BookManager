package com.pvthuan.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.pvthuan.book.model.Book;
import com.pvthuan.book.service.IBookService;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	@Autowired
	IBookService bookService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>>  fetchAll(){
		List<Book> lists = bookService.findAll();
		if(lists.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(lists, HttpStatus.OK);
	}
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> findOne(@PathVariable long id){
		Book b = bookService.findOne(id);
		if(b == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(b, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void>  create(@RequestBody Book book,UriComponentsBuilder ucBuilder){
//		Book b = bookService.findName(book.getB_title());
//		System.out.println(book.getB_year());
//		if(b.getB_title().equals(book.getB_title())) {
//			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//		}
		bookService.create(book);
		HttpHeaders headers = new HttpHeaders();
		Book temp = bookService.findName(book.getName());
		headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(temp.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> delete(@PathVariable long id){
		Book b = bookService.findOne(id);
		if(b == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		bookService.deleteById(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> update(@PathVariable long id,@RequestBody Book book){
		Book temp = bookService.findOne(id);
		if(temp == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		book.setId(id);
		bookService.update(book);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
}
