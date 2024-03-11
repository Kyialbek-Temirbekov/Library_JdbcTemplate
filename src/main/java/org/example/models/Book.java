package org.example.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Title should not be empty")
    @Size(min=1, max=50, message = "Title should be in range 1 - 50")
    private String title;
    @NotEmpty(message = "Author name should not be empty")
    @Size(min=1, max=50, message = "Author name should be in range 1 - 50")
    private String author;
    @Min(value = 0, message = "Invalid year")
    @Max(value = 2050, message = "Invalid year")
    private int year;

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
