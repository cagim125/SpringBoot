package com.baeksoo.shop.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Book result = bookService.getBook(id);
        model.addAttribute("book", result);
        return "book/detail";
    }

    @GetMapping("/edit/{id}")
    public String editP(@PathVariable Long id, Model model) {
        Book result = bookService.getBook(id);
        model.addAttribute("book", result);
        return "book/edit";
    }

    @PostMapping("/edit")
    public String editProc(@ModelAttribute RequestDto request, Model model) {
        Book result = bookService.editBook(request);
        model.addAttribute("book", result);
        return "redirect:/api/book/edit/" + result.getId();
    }


}
