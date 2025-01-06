package com.Benkyoukai.Service;

import com.Benkyoukai.classOne.DTO.UserInfoDto;
import com.Benkyoukai.classOne.Mapper.RegisterMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final RegisterMapper registerMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(RegisterMapper registerMapper, PasswordEncoder passwordEncoder) {
        this.registerMapper = registerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void Registry(UserInfoDto userInfo){

        userInfo.setPw(passwordEncoder.encode(userInfo.getPw()));
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

    public int getUserAuthenticate(String id,String pw){
        UserInfoDto userInfoDto = registerMapper.selectUserById(id);
        if(passwordEncoder.matches(pw,userInfoDto.getPw())){
            return 1;
        }
        return 0;
    }

    public int updateUserById(UserInfoDto userInfoDto){
        return registerMapper.updateUserById(userInfoDto);
    }
}
