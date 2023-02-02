package com.hospitaltask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    final String FROM_EMAIL="testdemo000011@gmail.com";
    private String to;
    private String subject;
}
