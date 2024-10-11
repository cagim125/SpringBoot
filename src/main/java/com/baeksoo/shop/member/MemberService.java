package com.baeksoo.shop.member;

import com.baeksoo.shop.exception.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(MemberReqDto request) {
        if (memberRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateMemberException("이미 존재하는 회원입니다.");
        }

        var hash = passwordEncoder.encode(request.getPassword());
        Member member = Member.builder()
                .username(request.getUsername())
                .password(hash)
                .displayName(request.getDisplayName())
                .build();

        memberRepository.save(member);
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Boolean existingUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

}
