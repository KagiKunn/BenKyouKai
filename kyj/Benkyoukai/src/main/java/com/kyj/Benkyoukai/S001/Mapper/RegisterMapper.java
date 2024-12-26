package com.kyj.Benkyoukai.S001.Mapper;

import com.kyj.Benkyoukai.S001.DTO.RegisterDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegisterMapper {

    @Insert("insert into Account (id, pw, name, age) values (#{id}, #{pw}, #{name}, #{age})")
    int registerAccount (RegisterDTO registerDTO);

    @Select("select * from Account")
    List<RegisterDTO> selectAccounts(RegisterDTO registerDTO);
}
