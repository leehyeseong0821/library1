package com.korit.library.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    @ApiModelProperty(hidden = true)
    private  int userId;

    @NotBlank
    @ApiModelProperty(name="username",value = "사용자이름",example = "abc",required = true)
    private String username;
    @NotBlank
    @ApiModelProperty(name="password",value = "비밀번호",example = "123",required = true)
    private String password;

    @NotBlank
    @ApiModelProperty(name="repassword",value = "비밀번호 확인",example = "123",required = true)
    private String repassword;

    @NotBlank
    @ApiModelProperty(name="name",value = "성명",example = "이혜성",required = true)
    private String name;

    @NotBlank
    @Email
    @ApiModelProperty(name="email",value = "이메일",example = "lhs5567@naver.com",required = true)
    private String email;

    //권한은 여러개로 들고올 수 있기 때문에 List.
    @ApiModelProperty(hidden = true)
    private LocalDateTime createDate;
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateDate;

    @ApiModelProperty(hidden = true)
    private List<RoleDtlDto> roleDtlDto;
}
