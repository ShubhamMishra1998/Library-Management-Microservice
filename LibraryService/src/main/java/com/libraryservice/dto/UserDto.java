package com.libraryservice.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserDto {
    @NotNull(message = "Username should not be null")
    private String username;
    @NotNull(message = "Name should not be null")
    private String name;
    @Email(message = "please enter correct email")
    private String email;
}
