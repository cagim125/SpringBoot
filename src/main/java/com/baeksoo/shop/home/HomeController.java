package com.baeksoo.shop.home;

import com.baeksoo.shop.book.Book;
import com.baeksoo.shop.book.BookService;
import com.baeksoo.shop.exception.DuplicateMemberException;
import com.baeksoo.shop.member.CustomUser;
import com.baeksoo.shop.member.MemberReqDto;
import com.baeksoo.shop.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BookService bookService;

    @GetMapping("/")
    public String Home() {
        return "redirect:/list/1";
    }
    @GetMapping("/list/{page}")
    public String ListPage(Model model, @PathVariable Integer page) {
        Page<Book> result = bookService.findPage(page);

        // 화면에 표시할 page 갯수
        int pageSize = 10;
        // 총 page 갯수
        int totalPage = (int) Math.ceil((double) result.getTotalPages() / pageSize);

        // 시작 페이지와 끝 페이지 계산 (1-10, 11-20 등)
        int startPage = ((page - 1) / 10) * 10 + 1;
        int endPage = Math.min(startPage + 9, totalPage);

        model.addAttribute("currentPage", page);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("books", result.getContent());

        return "home";
    }



}
