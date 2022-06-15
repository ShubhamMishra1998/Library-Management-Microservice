package com.libraryservice.service;

import com.libraryservice.clients.BookClient;
import com.libraryservice.clients.UserClient;
import com.libraryservice.dto.BookDto;
import com.libraryservice.dto.LibraryDto;
import com.libraryservice.dto.UserDto;
import com.libraryservice.dto.UserWithBookDto;
import com.libraryservice.entity.Library;
import com.libraryservice.exceptions.LibraryException;
import com.libraryservice.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService{
    @Autowired
    private LibraryRepository repository;
    @Autowired
    private UserClient userClient;
    @Autowired
    private BookClient bookClient;

    private static final int BOOK_LIMIT=3;

    @Override
    public List<BookDto> getAllBooks(){
        return Optional.ofNullable(bookClient.getBooks()).
                map(ResponseEntity::getBody)
                .orElseGet(ArrayList::new);
    }

    @Override
    public BookDto getBook(int id){
       return Optional.ofNullable(bookClient.getBook(id))
               .map(ResponseEntity::getBody)
               .orElseThrow(()-> new LibraryException("Book with this id does not exist"));
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        return Optional.ofNullable(bookClient.addBook(bookDto))
                .map(ResponseEntity::getBody)
                .orElseThrow(()->new LibraryException("Book cannot be added"));
    }

    @Override
    public BookDto updateBook(int id, BookDto bookDto) {
       return Optional.ofNullable(bookClient.updateBook(id,bookDto))
               .map(ResponseEntity::getBody)
               .orElseThrow(()->new LibraryException("book cannot be updated"));
    }

    @Override
    public BookDto deleteBook(int id) {
      return Optional.ofNullable(bookClient.deleteBook(id))
              .map(ResponseEntity::getBody)
              .orElseThrow(()->new LibraryException("book with given id does not exist"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return Optional.ofNullable(userClient.getAllUsers())
                .map(ResponseEntity::getBody)
                .orElseGet(ArrayList::new);
    }

    @Override
    public UserWithBookDto getUser(String username) {
       UserDto userDto=Optional.ofNullable(userClient.getUser(username))
               .map(ResponseEntity::getBody)
               .orElseThrow(()->new LibraryException("user not found"));
       List<BookDto> books=getBooksAssignedToUser(username);
       return UserWithBookDto.builder()
               .username(userDto.getUsername())
               .email(userDto.getEmail())
               .name(userDto.getName())
               .books(books).build();
    }

    private List<BookDto> getBooksAssignedToUser(String username) {

        List<BookDto> books=new ArrayList<>();
        repository.findByUsername(username).forEach(e->books.add(bookClient.getBook(e.getBookId()).getBody()));
        return books;
    }


    @Override
    public UserDto addUser(UserDto userDto){
        return Optional.ofNullable(userClient.addUser(userDto))
                .map(ResponseEntity::getBody)
                .orElseThrow(()->new LibraryException("user cannot be added"));
    }

    @Override
    public UserDto deleteUser(String username){
        UserDto userDto=Optional.ofNullable(userClient.deleteUser(username))
                .map(ResponseEntity::getBody)
                .orElseThrow(()->new LibraryException("User cannot be deleted"));

           if(Boolean.TRUE.equals(repository.deleteAllUserByUsername(username))){
               repository.deleteAllUserByUsername(username);
           }
        return userDto;
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) {
       return Optional.ofNullable(userClient.updateUser(username,userDto))
               .map(ResponseEntity::getBody)
               .orElseThrow(()->new LibraryException("User cannot be updated"));
    }

    @Override
    public BookDto issueBook(String username, int bookId) {
        BookDto bookDto=Optional.ofNullable(bookClient.getBook(bookId))
                .map(ResponseEntity::getBody)
                .orElseThrow(()->new LibraryException("book not found"));
        UserDto userDto=Optional.ofNullable(userClient.getUser(username))
                .map(ResponseEntity::getBody)
                .orElseThrow(()->new LibraryException("user not found"));

        if(Optional.ofNullable(repository.findUserByUsernameAndBookId(username,bookId)).isPresent()){
            throw  new LibraryException("this book is already issued to please select other book");
        }

        if(hasReachedMaxRange(username)){
            throw  new LibraryException("please release some book to get more");
        }

        Library library=Library.builder().username(userDto.getUsername())
                .bookId(bookId).build();
        repository.save(library);
        return bookDto;
    }

    @Override
    public LibraryDto deleteBookFromUser(String username, int bookId) {
        Library library=Optional.ofNullable(repository.findUserByUsernameAndBookId(username,bookId))
                .orElseThrow(()->new LibraryException("user cannot be found"));
         repository.delete(library);
         return LibraryDto.builder().username(library.getUsername())
                 .bookId(library.getBookId()).build();

    }

    private boolean hasReachedMaxRange(String username){
        return repository.findByUsername(username).size()==BOOK_LIMIT;
    }
}
