package com.pvthuan.book.dao;

import java.util.List;

import com.pvthuan.book.model.Book;
import org.springframework.data.jpa.repository.Query;

public interface IBookDAO {
	Book findOne(final long id);
	@Query("SELECT b FROM Book b where b.b_title = :name") 
	Book findByName(final String name);
	
	List<Book> findAll();
	
	void create(final Book book);
	
	void delete(final Book book);
	
	void deleteById(final long id);
	
	Book update(final Book book);
}
