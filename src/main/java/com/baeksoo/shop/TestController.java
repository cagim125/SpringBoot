package com.baeksoo.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @GetMapping("/about")
    @ResponseBody
    public String about() {
        return "about";
    }

    @GetMapping("/mypage")
    @ResponseBody
    public String mypage() {
        return "mypage";
    }
}
