package com.libraryservice.controller;

import com.libraryservice.dto.BookDto;
import com.libraryservice.dto.LibraryDto;
import com.libraryservice.dto.UserDto;
import com.libraryservice.dto.UserWithBookDto;
import com.libraryservice.service.LibraryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryServiceImpl libraryService;
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.getAllBooks());
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable int id){
        return new ResponseEntity<>(libraryService.getBook(id),HttpStatus.OK);
    }
    @PostMapping("/books")
    public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto bookDto){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.addBook(bookDto));
    }

    @PutMapping("/`books/{bookId}")
    public ResponseEntity<BookDto> updateBook(@PathVariable int bookId,@RequestBody @Valid BookDto bookDto){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.updateBook(bookId,bookDto));
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable int bookId){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.deleteBook(bookId));
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.getAllUsers());
    }
    @GetMapping("/users/{username}")
    public ResponseEntity<UserWithBookDto> getUser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.getUser(username));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.addUser(userDto));
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.deleteUser(username));
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username,@RequestBody @Valid UserDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.updateUser(username,userDto));
    }

    @PostMapping("/users/{username}/books/{bookId}")
    public ResponseEntity<BookDto> issueBook(@PathVariable String username,@PathVariable int bookId){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.issueBook(username,bookId));
    }

    @DeleteMapping("/users/{username}/books/{bookId}")
    public ResponseEntity<LibraryDto> deleteBookFromUser(String username,int bookId){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.deleteBookFromUser(username, bookId));
    }

}
