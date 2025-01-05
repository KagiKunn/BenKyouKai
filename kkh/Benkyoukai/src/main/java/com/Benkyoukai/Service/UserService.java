package com.Benkyoukai.Service;

import com.Benkyoukai.classOne.DTO.UserInfoDto;
import com.Benkyoukai.classOne.Mapper.RegisterMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final RegisterMapper registerMapper;

    public UserService(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

    public void Registry(UserInfoDto userInfo){
        registerMapper.insertUser(userInfo);
    }

    public List<UserInfoDto> getAllUser(){
        return registerMapper.selectAllUserInfo();
    }

    public UserInfoDto getUserById(String id){
        return registerMapper.selectUserById(id);
    }
    public int getCountUserById(String id){
        return registerMapper.countUserById(id);
    }

    public int deleteUserById(String id){
        return registerMapper.deleteUserById(id);
    }

    public int getUserByIdAndPw(String id,String pw){
        return registerMapper.countUserByIdAndPw(id,pw);
    }

    public int updateUserById(UserInfoDto userInfoDto){
        return registerMapper.updateUserById(userInfoDto);
    }
}
