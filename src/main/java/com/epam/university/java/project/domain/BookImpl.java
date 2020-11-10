package com.epam.university.java.project.domain;

import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

public class BookImpl implements Book {
    private int id;
    private String title;
    private Collection<String> authors;
    private String serialNumber;
    private LocalDate returnDate;
    private BookStatus status;
    private StateMachineDefinition<BookStatus, BookEvent> definition;

    public BookImpl(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Collection<String> getAuthors() {
        return authors;
    }

    @Override
    public void setAuthors(Collection<String> authors) {
        this.authors = authors;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    @Override
    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public void setReturnDate(LocalDate date) {
        this.returnDate = date;
    }

    @Override
    public BookStatus getState() {
        return status;
    }

    @Override
    public void setState(BookStatus bookStatus) {
        this.status = bookStatus;
    }

    @Override
    public StateMachineDefinition<BookStatus, BookEvent> getStateMachineDefinition() {
        return definition;
    }

    @Override
    public void setStateMachineDefinition(StateMachineDefinition<BookStatus,
            BookEvent> definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BookImpl)) {
            return false;
        }

        BookImpl book = (BookImpl) o;
        return getId() == book.getId()
                && Objects.equals(getTitle(), book.getTitle())
                && Objects.equals(getAuthors(), book.getAuthors())
                && Objects.equals(getSerialNumber(), book.getSerialNumber())
                && Objects.equals(getReturnDate(), book.getReturnDate())
                && status == book.status
                && Objects.equals(definition, book.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getTitle(),
                getAuthors(),
                getSerialNumber(),
                getReturnDate(),
                status,
                definition);
    }
}
