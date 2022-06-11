package com.bookservice.service;

import com.bookservice.dto.BookDto;
import com.bookservice.entity.Book;
import com.bookservice.exception.BookException;
import com.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private static final String MESSAGE="book not found";
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public BookDto findBook(int id) throws BookException {
        Book book=bookRepository.findById(id).orElseThrow(()->new BookException(MESSAGE));
        return BookDto.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .build();
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book=Book.builder()
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .build();
        bookRepository.save(book);
        return bookDto;
    }

    @Override
    public BookDto deleteBook(int id) throws BookException {
        Book book=bookRepository.findById(id).orElseThrow(()->new BookException(MESSAGE));
        bookRepository.delete(book);
        return BookDto.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .build();
    }

    @Override
    public BookDto updateBook(int id, BookDto bookDto) throws BookException {
        Book book=bookRepository.findById(id).orElseThrow(()->new BookException(MESSAGE));
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        bookRepository.save(book);
        return bookDto;
    }
}
