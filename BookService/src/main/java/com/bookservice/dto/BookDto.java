package com.bookservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    @NotNull(message = "name should be not be null")
    private String name;
    @NotNull(message = "name should be not be null")
    private String publisher;
    @NotNull(message = "name should be not be null")
    private String author;
}
