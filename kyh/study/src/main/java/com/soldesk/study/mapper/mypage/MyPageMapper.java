package com.soldesk.study.mapper.mypage;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MyPageMapper {
    @Delete("delete from User where id = #{id}")
    int deleteUser(String id);

    @Update("update User set id = #{id},pw = #{pw},nick = #{nick}, img = #{img} where id = #{login_id}")
    int updateUser(String id, String pw, String nick, String img, String login_id);
}
