package com.cba.book.controller;

import com.cba.book.exception.CustomBookCatalogueException;
import com.cba.book.model.BookCatalogue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cba.book.repository.BookCatalogueRepository;
import com.cba.book.service.BookCatalogueService;
import com.cba.book.exception.NoSuchBookExistsException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api")
public class BookCatalogueController {

    @Autowired
    private BookCatalogueService bookCatalogueService;
    @Autowired
    private BookCatalogueRepository bookCatalogueRepository;

    @GetMapping("/books")
    public ResponseEntity<List<BookCatalogue>> getAllBooks(){
        return new ResponseEntity<>(bookCatalogueService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<BookCatalogue> addBook( @Valid @RequestBody BookCatalogue bookDetails){

        if (bookDetails != null && bookDetails.getTitle() != null) {
            return new ResponseEntity<>(bookCatalogueService.addBook(bookDetails), HttpStatus.CREATED);
        } else {
            throw new CustomBookCatalogueException("title cannot be null");
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<BookCatalogue> updateBook(@Valid @PathVariable("id")final Long id, @RequestBody final BookCatalogue bookDetails){
        BookCatalogue bookCatalogue = getBookDet(id);
        if (bookCatalogue != null) {
            bookDetails.setId(id);
            return new ResponseEntity<>(bookCatalogueService.updateBook(bookDetails), HttpStatus.OK);
        } else {
            throw new NoSuchBookExistsException("No Such book exists!!");
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deleteBookByIsbn(@PathVariable("id") long id) {
        BookCatalogue bc = getBookDet(id);

        if (bc != null) {
           bookCatalogueService.deleteBookByIsbn(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new NoSuchBookExistsException("No Such book exists!!");
        }
    }
    @GetMapping("/book")
    public ResponseEntity<List<BookCatalogue>> getBooksByTitleAndAuthor(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author){
        return new ResponseEntity<>(bookCatalogueService.getBooksByTitleAndAuthor(title, author), HttpStatus.OK);
    }


    private BookCatalogue getBookDet(long id) {
        Optional<BookCatalogue> bookObj = bookCatalogueRepository.findById(id);
        if (bookObj.isPresent()) {
            return bookObj.get();
        }
        return null;
    }
}
