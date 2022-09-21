package ru.anfilofyev.anfilofyev.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.anfilofyev.anfilofyev.persist.Product;
import ru.anfilofyev.anfilofyev.persist.ProductRepository;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String listPage(@RequestParam Optional<String> titleFilter, Model model) {
        if (titleFilter.isEmpty() || titleFilter.get().isBlank()) {
            model.addAttribute("products", productRepository.findAll());
        } else {
            model.addAttribute("products", productRepository.findAllByTitleLike("%" + titleFilter.get() + "%"));
        }
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("", 0f, "", false));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        log.info("The product properties have changed: " + product.getId() + " || " + product.getTitle() + " || " + product.getPrice());
        productRepository.save(product);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        productRepository.save(product);
        return "redirect:/product";
    }



}
