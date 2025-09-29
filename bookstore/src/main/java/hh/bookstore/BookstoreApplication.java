package hh.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.Category;
import hh.bookstore.repositories.BookRepository;
import hh.bookstore.repositories.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
        return (args) -> {
            
            // Testidata:

            Category fiction = categoryRepository.save(new Category("Fiktio"));
            Category nonFiction = categoryRepository.save(new Category("Tietokirja"));
            Category children = categoryRepository.save(new Category("Lastenkirja"));

            bookRepository.save(new Book("Kirja1", "Villeteus", 2003, "1231412123123", 29.99, fiction));
            bookRepository.save(new Book("Kirja2", "Joku random kundi", 2004, "1231412123124", 39.59, nonFiction));
            bookRepository.save(new Book("Kirja3", "kirjailija3", 2013, "1231412123125", 30.00, children));

            System.out.println("Books in database:");
            for (Book book : bookRepository.findAll()) {
                System.out.println(book);
            }

            System.out.println("Categories in database:");
            for (Category category : categoryRepository.findAll()) {
                System.out.println(category);
            }
        };
    }
}
