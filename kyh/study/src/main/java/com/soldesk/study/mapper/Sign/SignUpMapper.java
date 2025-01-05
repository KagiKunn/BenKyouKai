package com.soldesk.study.mapper.Sign;

import com.soldesk.study.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SignUpMapper {

    @Insert("insert into User values(null, #{id}, #{pw}, #{nick}, #{age})")
    int insertUser(UserDTO userDTO);

    @Select("select * from User")
    List<UserDTO> getAllUsers();

    @Select("select * from User where id = #{id}")
    Boolean getUserById(String id);

    @Select("select * from User where id = #{id}")
    UserDTO getUserDTOById(String id);
}
