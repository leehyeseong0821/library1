package com.korit.library.api;


import com.korit.library.aop.annotation.ValidAspect;
import com.korit.library.security.PrincipalDetails;
import com.korit.library.service.AccountService;
import com.korit.library.web.dto.CMRespDto;
import com.korit.library.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountApi {

    @Autowired
    private AccountService accountService;
    @ValidAspect
    @PostMapping("/register")            //회원가입 정보를 JSON으로 RequestBody ? extends CMRespDto<? extends UserDto>
    public ResponseEntity<?> register(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {

        accountService.duplicateUsername(userDto.getUsername());
        accountService.compareToPassword(userDto.getPassword(), userDto.getRepassword());

        UserDto user = accountService.registerUser(userDto);

        return ResponseEntity
                .created(URI.create("/api/account/user/"+user.getUserId()))
                .body(new CMRespDto<>("Create a new User", user));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId) {
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>("Success", accountService.getUser(userId)));
    }

//    @ApiOperation(value = "Get Principal", notes = "로그인된 사용자 정보 가져오기")
//
//    @GetMapping("/principal")
//    public ResponseEntity<CMRespDto<? extends PrincipalDetails>> getPrincipalDetails(@AuthenticationPrincipal PrincipalDetails principalDetails){
//        principalDetails.getAuthorities().forEach(role ->{
//            log.info("로그인된 사용자의 권한:{}", role.getAuthority());
//        });
//
//
//    }
}