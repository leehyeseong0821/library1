package com.korit.library.api;


import com.korit.library.web.dto.CMRespDto;
import com.korit.library.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountApi {
//    회원가입 PostMapping 사용
    @PostMapping("/register")
                                        //회원가입 정보를 JSON으로 가져올거고 벨리데이션 체크도 해야한다.
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Create a new User",null));
    }
}
