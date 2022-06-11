package com.bookservice.controller;

import com.bookservice.dto.BookDto;
import com.bookservice.entity.Book;
import com.bookservice.exception.BookException;
import com.bookservice.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findBook(@PathVariable int id) throws BookException {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBook(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto bookDto){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.createBook(bookDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable int id,@RequestBody @Valid BookDto bookDto) throws BookException{
       return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id,bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable int id) throws BookException{
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(id));
    }


}
