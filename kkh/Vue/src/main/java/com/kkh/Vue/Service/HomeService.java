package com.kkh.Vue.Service;

import com.kkh.Vue.DTO.UserInfoDto;
import com.kkh.Vue.Mapper.HomeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    private final HomeMapper homeMapper;

    public HomeService(HomeMapper homemapper) {
        this.homeMapper = homemapper;
    }
    public List<UserInfoDto> findAllUser() {
        return homeMapper.findAll();
    }
}
