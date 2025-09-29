package hh.bookstore.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import hh.bookstore.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByTitle(String title);
}
