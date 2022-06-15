package com.libraryservice.clients;

import com.libraryservice.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "BookService/books")
public interface BookClient {
    @GetMapping
    ResponseEntity<List<BookDto>> getBooks();

    @GetMapping("/{bookId}")
    ResponseEntity<BookDto> getBook(@PathVariable int bookId);

    @PostMapping
    ResponseEntity<BookDto> addBook(BookDto bookDto);

    @PutMapping("/{bookId}")
    ResponseEntity<BookDto> updateBook(@PathVariable int bookId, @RequestBody @Valid BookDto bookDto);

    @DeleteMapping("/{bookId}")
    ResponseEntity<BookDto> deleteBook(@PathVariable int bookId);



}
