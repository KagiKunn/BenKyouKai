package com.ltk.Benkyoukai.service;

import com.ltk.Benkyoukai.dto.BenkyoukaiVO;
import com.ltk.Benkyoukai.mapper.BenkyoukaiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenkyoukaiService {

   @Autowired BenkyoukaiMapper benkyoukaiMapper;

   @Autowired
   PasswordEncoder passwordEncoder;

//   public void registerUser(String id, String rawPassword) {
//       String encodePassword = passwordEncoder.encode(rawPassword);
//       System.out.println("암호화된 비번: " + encodePassword);
//   }

   public boolean isIdDuplicate(String id) {
       System.out.println( "중복확인 : " + benkyoukaiMapper.checkIdDuplicate(id));
       if (benkyoukaiMapper.checkIdDuplicate(id) == 1){
           return true;
       }
       return false;
   }

   public BenkyoukaiVO getUserById(String id) {
       return benkyoukaiMapper.getUserByID(id);
   }

   public void updateUser(BenkyoukaiVO benkyoukaiVO) {
       benkyoukaiMapper.updateUser(benkyoukaiVO);
   }

    public void registerUser(BenkyoukaiVO benkyoukaiVO) {

       String encodePassword = passwordEncoder.encode(benkyoukaiVO.getPassword());
       System.out.println("암호화된 비번: " + encodePassword);

       benkyoukaiVO.setPassword(encodePassword);

       benkyoukaiMapper.insertUsers(benkyoukaiVO);

    }

    public List<BenkyoukaiVO> getAllUsers() {
        return benkyoukaiMapper.getAllUsers();

    }

    public BenkyoukaiVO getUser(String id, String password) {

        return benkyoukaiMapper.getUser(id, password);
    }

    public void deleteUser(String id) {

       benkyoukaiMapper.deleteUser(id);
    }
}
