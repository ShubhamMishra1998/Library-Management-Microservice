package com.libraryservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "library")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private int bookId;

}
