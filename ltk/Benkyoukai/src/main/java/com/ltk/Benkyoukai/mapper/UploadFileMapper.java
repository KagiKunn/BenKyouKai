package com.ltk.Benkyoukai.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UploadFileMapper {

    @Insert("INSERT INTO Upload (files) VALUES (#{files})")
    int UploadFile(@Param("files") String files);
}
