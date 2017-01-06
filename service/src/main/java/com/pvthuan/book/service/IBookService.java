package com.pvthuan.book.service;

import java.util.List;

import com.pvthuan.book.model.Book;

public interface IBookService {
	Book findOne(final long id);

	Book findName(final String name);

	List<Book> findAll();

	void create(final Book book);

	void delete(final Book book);

	void deleteById(final long id);

	Book update(final Book book);
}
