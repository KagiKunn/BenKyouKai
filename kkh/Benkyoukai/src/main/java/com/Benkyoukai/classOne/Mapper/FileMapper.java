package com.Benkyoukai.classOne.Mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Insert("insert into image (name) VALUES (#{img})")
    int insertImage(@Param("img") String img);
}