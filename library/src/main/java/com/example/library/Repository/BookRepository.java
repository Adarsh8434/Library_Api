package com.example.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.library.modal.book;

@Repository
public interface BookRepository extends JpaRepository<book,Integer> {

}
