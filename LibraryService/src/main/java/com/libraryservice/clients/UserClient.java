package com.libraryservice.clients;

import com.libraryservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "UserService/users")
public interface UserClient {

    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers();

    @GetMapping("/{userName}")
    ResponseEntity<UserDto> getUser(@PathVariable @Valid String userName);

    @PostMapping
    ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto);

    @PutMapping("/{userName}")
    ResponseEntity<UserDto> updateUser(@PathVariable @Valid String userName, @RequestBody @Valid UserDto userDto);

    @DeleteMapping("/{userName}")
    ResponseEntity<UserDto> deleteUser(@PathVariable @Valid String userName);

}
