package com.baeksoo.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("name", "홍길동");
        return "list";
    }
}
