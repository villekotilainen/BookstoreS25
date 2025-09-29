package hh.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import hh.bookstore.domain.Category;
import hh.bookstore.repositories.CategoryRepository;


@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(categoryDetails.getName());
            return categoryRepository.save(category);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }

    // Näyttää lomakkeen uuden kategorian luomiseen
    @GetMapping("/categories/new")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "newcategory";
    }

    // Tallentaa uuden kategorian lomakkeelta
    @PostMapping("/categories")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }
}

