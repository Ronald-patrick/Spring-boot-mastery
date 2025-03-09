package com.ronald.springboot.cruddemo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

}
