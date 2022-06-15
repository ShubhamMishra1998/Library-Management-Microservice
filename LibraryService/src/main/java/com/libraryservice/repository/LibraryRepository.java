package com.libraryservice.repository;

import com.libraryservice.entity.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibraryRepository extends CrudRepository<Library,Integer> {
    Boolean deleteAllUserByUsername(String username);
    Library findUserByUsernameAndBookId(String username, int bookId);
    List<Library> findByUsername(String username);
}
