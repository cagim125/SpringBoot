package com.baeksoo.shop.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping()
    public String noticeAll(Model model) {
        var result = noticeService.noticeFindAll();
        System.out.println(result.toString());
        model.addAttribute("notices", result);

        return "notice";
    }
}
