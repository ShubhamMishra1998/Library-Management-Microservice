package com.userservice.service;

import com.userservice.dto.UserDto;
import com.userservice.entity.User;
import com.userservice.exception.UserException;
import com.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    private static final String MESSAGE="user not found";

    @Override
    public List<User> findAllUsers() {
        return  (List<User>) userRepository.findAll();
    }

    @Override
    public UserDto findUserByUserName(String username) throws UserException {
         User user=userRepository.findUserByUsername(username).orElseThrow(()->new UserException(MESSAGE));
         return UserDto.builder().name(user.getName()).email(user.getEmail()).username(user.getUsername()).build();
    }

    @Override
    public UserDto deleteUser(String username) throws UserException{
        User user=userRepository.findUserByUsername(username).orElseThrow(()->new UserException(MESSAGE));
        userRepository.delete(user);
        return UserDto.builder().name(user.getName()).email(user.getEmail()).username(user.getUsername()).build();
    }

    @Override
    public UserDto updateUser(String username,UserDto userDto) throws UserException{
        User user=userRepository.findUserByUsername(userDto.getUsername()).orElseThrow(()->new UserException(MESSAGE));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
       userRepository.save(user);
       return userDto;

    }

    @Override
    public UserDto createUser(UserDto userDto) throws UserException {
       if(isUserPresent(userDto.getUsername())){
           throw new UserException("username taken please enter different username");
       }
       userRepository.save(User.builder().name(userDto.getName()).email(userDto.getEmail()).username(userDto.getUsername()).build());
       return userDto;
    }

    @Override
    public boolean isUserPresent(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }
}
