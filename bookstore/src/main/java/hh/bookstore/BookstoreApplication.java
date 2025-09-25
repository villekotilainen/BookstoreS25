package hh.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.domain.Book;
import hh.bookstore.repositories.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository) {
        return (args) -> {
            // Testidata:
            bookRepository.save(new Book("Clean Code", "Robert C. Martin", 2008, "9780132350884", 29.99));
            bookRepository.save(new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991", 39.99));
            bookRepository.save(new Book("Design Patterns", "Erich Gamma", 1994, "9780201633610", 49.99));
        };
    }
}
