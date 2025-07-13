package com.example.library.Controller;


import org.springframework.web.bind.annotation.RestController;

import com.example.library.Repository.BookRepository;
import com.example.library.modal.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




 @RestController
 @RequestMapping("/books")
public class bookController {
    
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/check")
    public String print() {
        return new String("Checking");
    }
  @PostMapping
  public String addBook(@RequestBody book b) {
      bookRepository.save(b);
      return "Book saved successfully";
  }
  @PutMapping("/{id}")
  public String updateBook(@PathVariable String id, @RequestBody book b) {
      book existingBook = bookRepository.findById(Integer.parseInt(id)).orElse(null);
      if (existingBook != null) {
          existingBook.setTitle(b.getTitle());
          existingBook.setAuthor(b.getAuthor());
          existingBook.setIsbn(b.getIsbn());
          bookRepository.save(existingBook);
          return "Book updated successfully";
      }
     else return "Book not found";
  }

  @GetMapping("/{id}")
 public ResponseEntity<book> getBookById(@PathVariable Integer id) {
    Optional<book> optionalBook = bookRepository.findById(id);
    return optionalBook
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}

  @GetMapping
  public List<book> ListOfBooks() {
    return bookRepository.findAll();
  }

@PutMapping("/{id}/availability")
public ResponseEntity<String> updateAvailability(@PathVariable int id, @RequestParam boolean available) {
    Optional<book> optionalBook = bookRepository.findById(id);
    if (optionalBook.isPresent()) {
        book b = optionalBook.get();
        b.setAvailable(available);
        bookRepository.save(b);
        return ResponseEntity.ok("Availability updated for book ID " + id);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
    }
}
@DeleteMapping("/{id}")
public ResponseEntity<String> deleteBook(@PathVariable int id) {
    if (bookRepository.existsById(id)) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok("Book with ID " + id + " deleted successfully.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
    }
}

  
  
}
