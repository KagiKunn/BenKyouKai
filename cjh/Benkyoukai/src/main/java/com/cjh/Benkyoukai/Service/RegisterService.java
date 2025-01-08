package com.cjh.Benkyoukai.Service;

import com.cjh.Benkyoukai.DTO.RegisterVO;
import com.cjh.Benkyoukai.Mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService implements RegisterMapper {

    @Autowired
    private RegisterMapper registerMapper;


    @Override
    public int registerAccount(RegisterVO registerVO) {
        return registerMapper.registerAccount(registerVO);
    }

    @Override
    public List<RegisterVO> selectAllAccount() {
        return registerMapper.selectAllAccount();
    }

    @Override
    public int loginAccount(RegisterVO registerVO) {
        return registerMapper.loginAccount(registerVO);
    }

    @Override
    public RegisterVO selectAccountById(RegisterVO registerVO) {
        return registerMapper.selectAccountById(registerVO);
    }

    @Override
    public int updateAccount(RegisterVO registerVO) {
        return registerMapper.updateAccount(registerVO);
    }

    @Override
    public int deleteAccountById(RegisterVO registerVO) {
        return registerMapper.deleteAccountById(registerVO);
    }

    @Override
    public int validChecker(RegisterVO registerVO) {
        return registerMapper.validChecker(registerVO);
    }

    @Override
    public String selectPasswordById(RegisterVO registerVO) {
        return registerMapper.selectPasswordById(registerVO);
    }


}
