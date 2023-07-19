package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name = "Users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "FirstName")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Role")
    private String role;
}
