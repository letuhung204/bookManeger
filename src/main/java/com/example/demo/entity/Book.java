package com.example.demo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book", schema = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Uid")
  int id;

  @Column(name = "code", nullable = false, unique = true)
  @NotNull(message = "Please enter code")
  @Length(max= 20,message="size don't bigger than 20")
  String code;

  @Column(name = "name", nullable = false)
  @NotNull(message = "name not null")
  @Length(max = 255, message = "size don't bigger than 255")
  String name;

  @Column(name = "description", nullable = false)
  @Length(max = 500, message = "size don't bigger than 255")
  @NotNull(message = "description not null")
  String description;

  @Column(name = "category", nullable = false)
  @Length(max = 255, message = "size don't bigger than 255")
  @NotNull(message = "category not null")
  String category;

  @Column(name = "author", nullable = false)
  @Length(max = 255, message = "size don't bigger than 255")
  @NotNull(message = "author not null")
  String author;

  @Column(name = "publisher", nullable = false)
  @Length(max = 255, message = "size don't bigger than 255")
  @NotNull(message = "publisher not null")
  String publisher;

  @Column(name = "createUser")
  @Length(max = 100, message = "size don't bigger than 100")
  @NotNull(message = "createUser not null")
  String createUser;

  @Column(name = "createDate", columnDefinition = "DATETIME", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull(message = "createDate not null")
  Date createDate;

  @Column(name = "updateUser", nullable = false)
  @Length(max = 100, message = "size don't bigger than 100")
  @NotNull(message = "updateUser not null")
  String updateUser;

  @Column(name = "updateDate", columnDefinition = "DATETIME", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @NotNull(message = "updateDate not null")
  Date updateDate;
}
