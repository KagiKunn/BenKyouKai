package com.ltk.Benkyoukai.mapper;

import com.ltk.Benkyoukai.dto.BenkyoukaiVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BenkyoukaiMapper {

    @Insert("INSERT INTO benkyoukai (id, password, nickName, age) VALUES (#{id}, #{password}, #{nickName}, #{age})")
    void insertUsers(BenkyoukaiVO benkyoukaiVO);

    @Select("SELECT * from benkyoukai")
    List<BenkyoukaiVO> getAllUsers();
}
