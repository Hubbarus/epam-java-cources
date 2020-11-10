package com.epam.university.java.project.service;

import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookImpl;
import com.epam.university.java.project.domain.BookStatus;

import java.util.ArrayList;
import java.util.Collection;

public class BookDaoXmlImpl implements BookDao {
    private int lastBookId = 0;
    private Collection<Book> library = new ArrayList<>();

    @Override
    public Book createBook() {
        BookImpl book = new BookImpl(++lastBookId);
        book.setState(BookStatus.DRAFT);
        return book;
    }

    @Override
    public Book getBook(int id) {
        for (Book book : library) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Collection<Book> getBooks() {
        return library;
    }

    @Override
    public void remove(Book book) {
        for (Book current : library) {
            if (current.equals(book)) {
                library.remove(book);
                return;
            }
        }
    }

    @Override
    public Book save(Book book) {
        library.add(book);
        return book;
    }
}
