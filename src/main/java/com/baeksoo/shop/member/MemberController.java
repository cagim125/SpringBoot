package com.baeksoo.shop.member;

import com.baeksoo.shop.exception.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signUp")
    public String signUpP() {
        return "book/signUp";
    }

    @PostMapping("/signUp")
    public String signUpProc(@ModelAttribute MemberReqDto request, Model model) {
        try {
            memberService.signUp(request);
            return "redirect:/api/user/signIn";
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
        var result = (CustomUser) auth.getPrincipal();
        if (auth != null) {
            System.out.println(result.getDisplayName());
        }

        return "book/mypage";
    }

    @GetMapping("/exists/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        var result = memberService.existingUsername(username);

//        System.out.println(result);

        return ResponseEntity.ok(result);
    }
}



