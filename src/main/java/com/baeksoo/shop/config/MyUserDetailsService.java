package com.baeksoo.shop.config;

import com.baeksoo.shop.member.MemberRepository;
import com.baeksoo.shop.member.MemberReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var member = memberRepository.findByUsername(username);
        if (member.isEmpty()) {
            throw new UsernameNotFoundException("해당 아이디가 존재 하지 않음");
        }
        var user = member.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getUsername().contains("admin")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

}
