package com.hospitaltask.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Email {
    //private final String FROM_EMAIL="testdemo000011@gmail.com";
    private String to;
    private String subject;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
    
}
