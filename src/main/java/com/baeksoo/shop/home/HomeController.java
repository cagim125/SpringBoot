package com.baeksoo.shop.home;

import com.baeksoo.shop.book.Book;
import com.baeksoo.shop.book.BookRepository;
import com.baeksoo.shop.book.BookService;
import com.baeksoo.shop.exception.DuplicateMemberException;
import com.baeksoo.shop.member.MemberReqDto;
import com.baeksoo.shop.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping("/")
    public String Home(Model model) {
        List<Book> result = bookService.findLimit30();
        System.out.println(result);
        model.addAttribute("books", result);
        return "home";
    }

    @GetMapping("/signUp")
    public String signUpP() {
        return "book/signUp";
    }

    @PostMapping("/signUp")
    public String signUpProc(@ModelAttribute MemberReqDto request, Model model) {
        try {
            memberService.signUp(request);
            return "redirect:/signIn";
        } catch (DuplicateMemberException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "book/signUp";
        }
    }

    @GetMapping("/signIn")
    public String signInP() {
        return "book/signIn";
    }

    @GetMapping("/myPage")
    public String myPageP(Authentication auth) {
        if (auth != null) {
            System.out.println(auth);
            System.out.println(auth.getName()); //아이디출력가능
            System.out.println(auth.isAuthenticated()); //로그인여부 검사가능
        }

        return "book/mypage";
    }

}
