package com.Benkyoukai.classOne.Mapper;

import com.Benkyoukai.classOne.DTO.UserInfoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Insert("INSERT INTO user (id,pw,name,age) VALUES (#{id}, #{pw}, #{name}, #{age})")
    int insertUser(UserInfoDto userInfoDto);

    @Select("SELECT * FROM user")
    List<UserInfoDto> selectAllUserInfo();

    @Select("SELECT * FROM user WHERE id=#{id}")
    UserInfoDto selectUserById(@Param("id") String id);

    @Select("SELECT COUNT(*) FROM `user` WHERE id=#{id}")
    int countUserById(@Param("id") String id);

    @Select("SELECT COUNT(*) FROM user WHERE id=#{id} AND pw=#{pw}")
    int countUserByIdAndPw(@Param("id") String id, @Param("pw") String pw);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUserById(@Param("id") String id);

    @Update("UPDATE user SET pw = #{pw}, name = #{name}, age = #{age} WHERE id = #{id}")
    int updateUserById(UserInfoDto userInfoDto);
}
