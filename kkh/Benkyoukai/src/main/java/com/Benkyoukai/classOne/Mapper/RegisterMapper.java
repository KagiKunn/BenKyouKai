package com.Benkyoukai.classOne.Mapper;

import com.Benkyoukai.classOne.DTO.UserInfoDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Insert("INSERT INTO user (id,pw,name,age) VALUES (#{id}, #{pw}, #{name}, #{age})")
    int insertUser(UserInfoDto userInfoDto);

    @Select("SELECT * FROM user")
    List<UserInfoDto> selectAllUserInfo();

    @Select("SELECT * FROM user WHERE id=#{id} LIMIT 1")
    UserInfoDto selectUserById(@Param("id") String id);
}
