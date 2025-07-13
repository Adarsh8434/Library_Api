package com.example.library.modal;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
@Getter
@Setter
@ToString
// @NoArgsConstructor
@Entity

public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     
    private int id;
    @NotBlank(message = "Title must not be blank")
    private String title;
    @NotBlank(message = "Author must not be blank")
    private String author; 
    @NotBlank(message = "ISBN must not be blank")
    private String isbn;
     @NotBlank(message = "Aailable must not be blank")
    private boolean available;
    public book() {

    }

    public book(int id, String title, String author, String isbn, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }
    //  public void setAvailable(boolean available) { this.available = available; }
}
