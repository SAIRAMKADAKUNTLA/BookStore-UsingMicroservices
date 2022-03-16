package com.bridgelabz.bookservices.service;

import com.bridgelabz.bookservices.Model.BookModel;
import com.bridgelabz.bookservices.dto.BookDto;


import java.util.List;

public interface BookService {

    List<BookModel> getAllData();

    BookModel addBook(BookDto bookDto);

    BookModel updateUser(Long id, BookDto bookDto);

    BookModel getData(Long id);

    void deleteData(Long id);
}
