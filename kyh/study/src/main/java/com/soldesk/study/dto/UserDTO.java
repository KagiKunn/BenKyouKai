package com.soldesk.study.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String pw;
    private String nick;
    private String age;

    public UserDTO(String id, String pw, String nick, String age) {
        this.id = id;
        this.pw = pw;
        this.nick = nick;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
