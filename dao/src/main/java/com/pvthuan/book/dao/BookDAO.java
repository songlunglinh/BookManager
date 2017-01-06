package com.pvthuan.book.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pvthuan.book.model.Book;

@Repository
public class BookDAO extends AbstractJpaDAO<Book> implements IBookDAO{
	public BookDAO() {
		super();
		setClazz(Book.class);
	}

	@Override
	public Book findByName(String name) {
		Query query = entityManager.createQuery("select b from Book b where name = :name", Book.class);
		query.setParameter("name", name);
		return (Book) query.getSingleResult();

	}

	
	
}