package com.kkh.Vue.Mapper;

import com.kkh.Vue.DTO.UserInfoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeMapper {
    @Select("SELECT * from user")
    List<UserInfoDto> findAll();
}

