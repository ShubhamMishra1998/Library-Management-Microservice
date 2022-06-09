package com.userservice.controller;

import com.userservice.dto.UserDto;
import com.userservice.entity.User;
import com.userservice.exception.UserException;
import com.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> findUser(@PathVariable String username) throws UserException {
       return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByUserName(username));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(UserDto userDto) throws UserException{
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userDto));
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username,UserDto userDto) throws UserException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(username,userDto));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable String username) throws UserException{
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(username));
    }


}
