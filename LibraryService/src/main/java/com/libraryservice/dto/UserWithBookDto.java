package com.libraryservice.dto;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserWithBookDto {
    @NotNull(message = "Username should not be null")
    private String username;
    @NotNull(message = "Name should not be null")
    private String name;
    @Email(message = "please enter correct email")
    private String email;
    @NotNull
    List<BookDto> books;

}
