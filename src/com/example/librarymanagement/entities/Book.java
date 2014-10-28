package com.example.librarymanagement.entities;

/**
 * Created by ligan_000 on 2014/10/23.
 */
public class Book {
    private int _id;
    private String book_class_number;
    private String book_name;
    private String author;
    private String press;
    private String press_date;
    private String contents;
    private int total_count;
    private int real_count;

    public Book() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getBook_class_number() {
        return book_class_number;
    }

    public void setBook_class_number(String book_class_number) {
        this.book_class_number = book_class_number;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPress_date() {
        return press_date;
    }

    public void setPress_date(String press_date) {
        this.press_date = press_date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getReal_count() {
        return real_count;
    }

    public void setReal_count(int real_count) {
        this.real_count = real_count;
    }
}
