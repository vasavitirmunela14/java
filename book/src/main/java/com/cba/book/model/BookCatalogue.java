package com.cba.book.model;
import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="book")
public class BookCatalogue {
    public BookCatalogue() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="ISBN")
    @Pattern(regexp = "(^$|[0-9]{13})", message = "ISBN should of 13 digit")

    private String ISBN;


    @Column(name="pdate")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date pdate;

    public BookCatalogue(Long id, String ISBN, String title, String author, Date pdate) {
        this.id = id;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.pdate = pdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
