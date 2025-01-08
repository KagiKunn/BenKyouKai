package com.soldesk.study.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String pw;
    private String nick;
    private String age;
    private String img;

    public UserDTO(String id, String pw, String nick, String age, String img) {
        this.id = id;
        this.pw = pw;
        this.nick = nick;
        this.age = age;
        this.img = img;
    }
}
