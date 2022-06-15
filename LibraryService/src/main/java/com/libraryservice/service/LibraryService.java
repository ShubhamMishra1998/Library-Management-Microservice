package com.libraryservice.service;

import com.libraryservice.dto.BookDto;
import com.libraryservice.dto.LibraryDto;
import com.libraryservice.dto.UserDto;
import com.libraryservice.dto.UserWithBookDto;
import java.util.List;

public interface LibraryService {
   List<BookDto> getAllBooks();
   BookDto getBook(int id);
   BookDto addBook(BookDto bookDto);
   BookDto updateBook(int id,BookDto bookDto);
   BookDto deleteBook(int id);
   List<UserDto> getAllUsers();
   UserWithBookDto getUser(String username);
   UserDto addUser(UserDto userDto);
   UserDto deleteUser(String username);
   UserDto updateUser(String username,UserDto userDto);
   BookDto issueBook(String username,int bookId);
   LibraryDto deleteBookFromUser(String username, int bookId);
}
