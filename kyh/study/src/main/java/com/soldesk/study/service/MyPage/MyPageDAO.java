package com.soldesk.study.service.MyPage;

import com.soldesk.study.mapper.mypage.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageDAO implements MyPageMapper {
    @Autowired
    private MyPageMapper myPageMapper;


    @Override
    public int deleteUser(String id) {
        return myPageMapper.deleteUser(id);
    }

    @Override
    public int updateUser(String id, String pw, String nick, String img, String login_user) {
        return myPageMapper.updateUser(id, pw, nick, img, login_user);
    }
}
