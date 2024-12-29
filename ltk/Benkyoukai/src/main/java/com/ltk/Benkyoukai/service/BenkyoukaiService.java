package com.ltk.Benkyoukai.service;

import com.ltk.Benkyoukai.dto.BenkyoukaiVO;
import com.ltk.Benkyoukai.mapper.BenkyoukaiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenkyoukaiService {

   @Autowired BenkyoukaiMapper benkyoukaiMapper;

    public void registerUser(BenkyoukaiVO benkyoukaiVO) {
        benkyoukaiMapper.insertUsers(benkyoukaiVO);
    }

    public List<BenkyoukaiVO> getAllUsers() {
        return benkyoukaiMapper.getAllUsers();
    }
}
