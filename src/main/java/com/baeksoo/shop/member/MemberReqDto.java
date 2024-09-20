package com.baeksoo.shop.member;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberReqDto {
    private String username;
    private String password;
    private String displayName;

    public MemberReqDto(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }
}
