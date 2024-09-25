package com.baeksoo.shop.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    @GetMapping
    public String all(Model model) {
        var result = bookRepository.findLimit100Book();
        model.addAttribute("books", result);
        return "book/list";
    }

    @GetMapping("/detail/{id}")
    public String detailP(@PathVariable Long id, Model model) {
        Book result =  bookService.Detail(id);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formatteredPrice = decimalFormat.format(result.getPrice());
        model.addAttribute("book", result);
        model.addAttribute("price", formatteredPrice);
        return "book/detail";
    }


}
