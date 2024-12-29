package com.soldesk.study.service.Sign;

import com.soldesk.study.dto.UserDTO;
import com.soldesk.study.mapper.Sign.SignUpMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpDAO implements SignUpMapper{
    @Autowired
    private SignUpMapper signUpMapper;

    @Override
    public int insertUser(UserDTO userDto) {
        return signUpMapper.insertUser(userDto);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return signUpMapper.getAllUsers();
    }

    @Override
    public Boolean getUserById(String id) { return signUpMapper.getUserById(id); }

    @Override
    public UserDTO getUserDTOById(String id) { return signUpMapper.getUserDTOById(id); }
}
