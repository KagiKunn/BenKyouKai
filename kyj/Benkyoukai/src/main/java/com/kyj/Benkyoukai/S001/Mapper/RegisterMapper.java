package com.kyj.Benkyoukai.S001.Mapper;

import com.kyj.Benkyoukai.S001.DTO.RegisterDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // == 이 클래스(RegisterMapper)는 요리사 역할을 수행한다.
public interface RegisterMapper {   // == 요리사가 따라야 할 메뉴판

    // 계정 등록 처리
    @Insert("insert into Account (id, pw, name, age) values (#{id}, #{pw}, #{name}, #{age})")
    // @Insert 어노테이션을 사용해 Java 메서드와 SQL 쿼리 연결.     // #{}는 MyBatis에서 사용되는 변수 표현식으로, RegisterDTO 객체에서 값을 읽어와 SQL에 삽입.
    int registerAccount (RegisterDTO registerDTO);

    // 등록된 계정 목록 조회
    @Select("select * from Account")
    List<RegisterDTO> selectAccounts(RegisterDTO registerDTO);

    // ID 중복 검사
    @Select("select count(*) from Account where id = #{id}")
    int checkDuplicateId(String id);
}
