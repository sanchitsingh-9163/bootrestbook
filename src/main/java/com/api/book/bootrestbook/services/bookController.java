package com.api.book.bootrestbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entity.Book;

@RestController
public class bookController {
    
    @Autowired
    private Bookservice bookservice;
    
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> list = this.bookservice.getAllBooks();

        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(bookservice.getAllBooks()));
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){

        Book book =this.bookservice.getBook(id); 
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(bookservice.getBook(id)));
        
    }

    @PostMapping("/books")
    public ResponseEntity<Optional<Book>> addBook(@RequestBody Book book){
        Book b=null;
    
        try{
            b=  this.bookservice.addBook(book);
            return ResponseEntity.ok(Optional.of(b));        
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") int id){
        this.bookservice.deleteBook(id);
    }

    @PutMapping("/book/{bookId}")
    public Book updateBook(@RequestBody Book book,@PathVariable int bookId){
        this.bookservice.updateBook(book,bookId);
        return book;
    }

}
