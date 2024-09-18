package com.baeksoo.shop.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    public String all(Model model) {
        var result = bookRepository.findLimit100Book();
        model.addAttribute("books", result);
        return "book/list";
    }
}
