package com.ltk.Benkyoukai.mapper;

import com.ltk.Benkyoukai.dto.BenkyoukaiVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BenkyoukaiMapper {

    @Select("SELECT * FROM benkyoukai WHERE id = #{id}")
    BenkyoukaiVO checkIdDuplicate(String id);

    @Insert("INSERT INTO benkyoukai (id, password, nickName, age) VALUES (#{id}, #{password}, #{nickName}, #{age})")
    void insertUsers(BenkyoukaiVO benkyoukaiVO);

    @Select("SELECT * from benkyoukai")
    List<BenkyoukaiVO> getAllUsers();

    @Select("SELECT * FROM benkyoukai WHERE id = #{id} AND password = #{password}")
    BenkyoukaiVO getUser(@Param("id") String id, @Param("password") String password); ;

    @Delete("DELETE FROM benkyoukai where id = #{id}")
    void deleteUser(String id);
}
