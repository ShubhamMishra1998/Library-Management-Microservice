package com.libraryservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibraryDto {
    @NotNull(message = "username should not be null")
    private String username;
    @NotNull(message = "book id should not be null")
    private int bookId;
}
