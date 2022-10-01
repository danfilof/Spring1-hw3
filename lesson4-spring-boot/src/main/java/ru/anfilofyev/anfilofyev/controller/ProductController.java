package ru.anfilofyev.anfilofyev.controller;


import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.anfilofyev.anfilofyev.model.Product;
import ru.anfilofyev.anfilofyev.model.dto.ProductDto;
import ru.anfilofyev.anfilofyev.repository.ProductRepository;
import ru.anfilofyev.anfilofyev.service.ProductService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    @GetMapping
//    public String listPage(@RequestParam Optional<String> titleFilter, Model model) {
//        if (titleFilter.isEmpty() || titleFilter.get().isBlank()) {
//            model.addAttribute("products", productRepository.findAll());
//        } else {
//            model.addAttribute("products", productRepository.findAllByTitleLike("%" + titleFilter.get() + "%"));
//        }
//        return "product";
//    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String titleFilter, Model model){

        model.addAttribute("products", productService.findAllByFilter(titleFilter));
        return "product";
    }



    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        productService.deleteProductById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid @ModelAttribute("product") ProductDto product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") ProductDto product) {
        productService.save(product);
        return "redirect:/product";
    }



}
