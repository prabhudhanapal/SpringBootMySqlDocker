package com.javasampleapproach.spring.mysql.repo;

import org.springframework.data.repository.CrudRepository;

import com.javasampleapproach.spring.mysql.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
