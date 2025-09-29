package hh.bookstore.web;

import hh.bookstore.domain.Book;
import hh.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Hakee kaikki kirjat
    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist"; // Palauttaa näkymän kirjalistan näyttämiseen
    }

    // Hakee lomakkeen uuden kirjan luomiseen
    @GetMapping("/books/new")
    public String getNewForm(Model model) {
        model.addAttribute("book", new Book());
        return "newbook"; // Palauttaa näkymän uuden kirjan lomakkeelle
    }

    // Tallentaa uuden kirjan
    @PostMapping("/books")
    public String saveNew(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books"; // Uudelleenohjaa kirjalistaan
    }

    // Poistaa kirjan ID:n perusteella
    @PostMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books"; // Uudelleenohjaa kirjalistaan
    }

    // Hakee lomakkeen olemassa olevan kirjan muokkaamiseen
    @GetMapping("/books/edit/{id}")
    public String getEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).stream().findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id)));
        return "editbook"; // Palauttaa näkymän muokkauslomakkeelle
    }

    // Tallentaa muokatun kirjan
    @PostMapping("/books/edit")
    public String saveEdited(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books"; // Uudelleenohjaa kirjalistaan
    }
}
