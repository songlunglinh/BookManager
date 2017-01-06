package com.pvthuan.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pvthuan.book.dao.IBookDAO;
import com.pvthuan.book.model.Book;

@Service
@Transactional
public class BookService implements IBookService{
	@Autowired
	IBookDAO bookDao;

	@Override
	public Book findOne(long id) {
		return bookDao.findOne(id);
	}

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public void create(Book book) {
		bookDao.create(book);
	}

	@Override
	public void delete(Book book) {
		bookDao.delete(book);
	}

	@Override
	public void deleteById(long id) {
		Book b = findOne(id);
		if(b!=null) {
			bookDao.delete(b);
		}
	}

	@Override
	public Book update(Book book) {
		return bookDao.update(book);
	}

	@Override
	public Book findName(String name) {
		return bookDao.findByName(name);
	}
	
	
}
