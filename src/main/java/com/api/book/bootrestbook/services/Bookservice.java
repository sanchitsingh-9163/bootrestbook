package com.api.book.bootrestbook.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entity.Book;

@Component
public class Bookservice {
    // private static List<Book> list = new ArrayList<Book>();
    // static{
    //     list.add(new Book(15,"first","abc"));
    //     list.add(new Book(13,"second","lmn"));
    //     list.add(new Book(14,"third","xyz"));
    // }
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        
        return (List<Book>)this.bookRepository.findAll();
    }

    public Book getBook(int id){
        return this.bookRepository.findById(id);
    }
    public Book addBook(Book b){

        this.bookRepository.save(b);
        return b;

    }

    public void deleteBook(int bid){
        this.bookRepository.deleteById(bid);
    }
    public void updateBook(Book book,int bookId){
        book.setId(bookId);
        this.bookRepository.save(book);
    }
}
