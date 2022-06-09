package com.userservice.service;

import com.userservice.dto.UserDto;
import com.userservice.entity.User;
import com.userservice.exception.UserException;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    UserDto findUserByUserName(String username) throws UserException;
    UserDto deleteUser(String username) throws UserException;
    UserDto updateUser(String username,UserDto userDto) throws UserException;
    UserDto createUser(UserDto userDto) throws UserException;
    boolean isUserPresent(String username);
}
