package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact 
{
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(nullable=false, unique=true, length=45)
 private String email;

 @Column(nullable=false, length=45)
 private String name;

 @Column(nullable=false, length=1000)
 private String message;
 
 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }
 
 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 } 
}