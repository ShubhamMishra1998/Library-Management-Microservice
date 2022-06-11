package com.bookservice.service;

import com.bookservice.dto.BookDto;
import com.bookservice.entity.Book;
import com.bookservice.exception.BookException;

import java.util.List;

public interface BookService {
     List<Book> findAllBooks();
     BookDto findBook(int id) throws BookException;
     BookDto createBook(BookDto bookDto);
     BookDto deleteBook(int id) throws BookException;
     BookDto updateBook(int id,BookDto bookDto) throws BookException;
}
