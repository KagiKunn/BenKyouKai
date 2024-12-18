package com.cjh.Benkyoukai.Mapper;


import com.cjh.Benkyoukai.DTO.RegisterVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Insert("INSERT INTO Account (id, pw, name, age) VALUES (#{id}, #{pw}, #{name}, #{age})")
    int registerAccount(RegisterVO registerVO);

    @Select("Select * from Account")
    List<RegisterVO> selectAllAccount();
}
