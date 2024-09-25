package com.baeksoo.shop.admin;

import com.baeksoo.shop.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final BookService bookService;

    @GetMapping("/add")
    public String write() {
        return "admin/write";
    }
    @PostMapping("/write")
    public String writeProc(@ModelAttribute("requestDto") RequestDto requestDto) {
        System.out.println(requestDto.toString());
        bookService.saveBook(requestDto);
        return "redirect:/myPage";
    }
}
