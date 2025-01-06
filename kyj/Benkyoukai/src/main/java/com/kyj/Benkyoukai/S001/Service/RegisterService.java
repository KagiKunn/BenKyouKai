package com.kyj.Benkyoukai.S001.Service;

import com.kyj.Benkyoukai.S001.DTO.RegisterDTO;
import com.kyj.Benkyoukai.S001.Mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService implements RegisterMapper {
    // == "나는 RegisterMapper 역할도 할 수 있어!"라고 주방장(Service)이 선언.
    // 하지만 실제 요리(DB 작업)는 요리사(RegisterMapper)에게 맡김.
    // 이를 통해 RegisterService는 RegisterMapper 타입으로 사용될 수 있다.
    @Autowired
    // @Autowired 사용 시, 스프링이 RegisterMapper 타입의 객체를 찾아서 registerMapper 변수에 자동 주입
    private RegisterMapper registerMapper;

    @Override
    // == 재정의 | "요리책의 레시피를 따라가면서, 나만의 방식으로 만든다"는 선언
    //            그러므로 요리사가 요리책(RegisterMapper)의 레시피를 재정의하지 않으면, 요리를 만들 수 없다.
    //            이건 무조건적인 규칙(요리사는 반드시 요리책의 모든 레시피를 재정의해야 한다" 이다.

    // 계정 등록 처리
    public int registerAccount(RegisterDTO registerDTO) {
        // ID 중복 검사
        int duplicateCount = registerMapper.checkDuplicateId(registerDTO.getId());
        if (duplicateCount > 0) {
            return 0;
        }
        return registerMapper.registerAccount(registerDTO);
    }
    // == 이 요리(registerAccount)는 레스토랑 어디에서든 웨이터가 주문할 수 있고(public),
    //    요리사(registerMapper)가 재료(RegisterDTO)로 요리를 만들고, 성공 여부를 숫자(int)로 알려준다.

    @Override
    // 등록된 계정 목록 조회
    public List<RegisterDTO> selectAccounts(RegisterDTO registerDTO) {
        return registerMapper.selectAccounts(registerDTO);
    }

    @Override
    // ID 중복 검사
    public int checkDuplicateId(String id) {
        System.out.println("Checking duplicate ID in Service: " + id); // 디버깅용 출력
        return registerMapper.checkDuplicateId(id);
    }
}
