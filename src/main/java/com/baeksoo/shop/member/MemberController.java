package com.baeksoo.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/1")
    @ResponseBody
    public MemberDto user() {
        var a = memberService.getMember(1L);
        MemberDto data = new MemberDto(a.getUsername(), a.getDisplayName());

        return data;
    }
}

class MemberDto {
    public String username;
    public String displayName;

    MemberDto(String a, String b){
        this.username = a;
        this.displayName = b;
    }
}
