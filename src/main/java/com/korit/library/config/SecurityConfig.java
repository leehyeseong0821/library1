package com.korit.library.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //WebSecurityConfigurerAdapter 상속을 받아서 웹시큐리티 설정으로 쓰겠다 기존의 씨큐리티설정은 버리는 의미
@Configuration //Bean객체 라이브러리에서 생성 강제로 IOC에 등록
                                            //상속을 받다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    //이렇게 세팅을 해주면 시큐리티 로그인을 하지 않아도 경로를 무시한다.보안을 걸지 않음.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()              //Path부터 스태틱 폴더
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable(); //기본적으로 제공하는 web basic로그인을 비활성화 한다.기존의 만든 로그인 페이지 사용예정
        http.authorizeRequests()
                .antMatchers("/mypage/**")//요청 주소로 앞에 mypage가들어오면 이거랑 뒤에 무엇이 들어오든지 인증이 필요
                .authenticated()
                .anyRequest()//다른 모든 요청들은
                .permitAll()//모든 권한을 준다 인증이 필요없다
                .and()
                .formLogin() //폼 로그인으로 한다
                .loginPage("/account/login") //로그인 페이지 get요청
                .loginProcessingUrl("/account/login")// 로그인 인증 post요청  기존 컨트롤러 login은 접속이 안되고 /accout/login으로 접속가능
                .defaultSuccessUrl("/index");
    }
}
