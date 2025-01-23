package com.cjh.Benkyoukai.Mapper;


import com.cjh.Benkyoukai.DTO.RegisterVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Insert("INSERT INTO Account (id, pw, name, age) VALUES (#{id}, #{pw}, #{name}, #{age})")
    int registerAccount(RegisterVO registerVO);

    @Select("Select * from Account")
    List<RegisterVO> selectAllAccount();

    @Select("SELECT COUNT(*) FROM Account WHERE id = #{id} AND pw = #{pw}")
    int loginAccount(RegisterVO registerVO);

    @Select("SELECT * FROM Account WHERE id = #{id}")
    RegisterVO selectAccountById(RegisterVO registerVO);

    @Update("update account set pw= #{pw} , name = #{name} , age = #{age} where id = #{id} ")
    int updateAccount(RegisterVO registerVO);

    @Delete("delete from account where id = #{id}")
    int deleteAccountById(RegisterVO registerVO);
    @Select("SELECT COUNT(*) FROM Account WHERE id = #{id} ")
    int validChecker(RegisterVO registerVO);

    @Select(("select pw from account where id = #{id}"))
    String selectPasswordById(RegisterVO registerVO);
}
