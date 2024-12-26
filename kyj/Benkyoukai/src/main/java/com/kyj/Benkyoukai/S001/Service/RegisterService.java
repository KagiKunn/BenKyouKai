package com.kyj.Benkyoukai.S001.Service;

import com.kyj.Benkyoukai.S001.DTO.RegisterDTO;
import com.kyj.Benkyoukai.S001.Mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService implements RegisterMapper {

    @Autowired
    private RegisterMapper registerMapper;


    @Override
    public int registerAccount(RegisterDTO registerDTO) {
        return registerMapper.registerAccount(registerDTO);
    }

    @Override
    public List<RegisterDTO> selectAccounts(RegisterDTO registerDTO) {
        return registerMapper.selectAccounts(registerDTO);
    }
}
