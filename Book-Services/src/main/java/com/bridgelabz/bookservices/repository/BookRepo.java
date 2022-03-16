package com.bridgelabz.bookservices.repository;


import com.bridgelabz.bookservices.Model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookModel,Long> {

}
