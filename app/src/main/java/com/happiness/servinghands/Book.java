package com.happiness.servinghands;

public class Book {
    public String BookName, AuthorName, BookCategory, BookAges, UID, Username, Email, Phone;

    public Book() {
    }

    public Book(String bookName, String authorName, String bookCategory, String bookAges, String UID, String username, String email, String phone) {
        BookName = bookName;
        AuthorName = authorName;
        BookCategory = bookCategory;
        BookAges = bookAges;
        this.UID = UID;
        Username = username;
        Email = email;
        Phone = phone;
    }
}
