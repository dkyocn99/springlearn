package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는것
@RequiredArgsConstructor
@Configuration //빈 등록(IoC관리)
@EnableWebSecurity //security 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private  final PrincipalDetailService principalDetailService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean //IoC가 되어 password 해쉬값을 스프링이 관리한다
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    //security가 대신 로그인을 해주는데 password를 가로채기를 하는데 해당 password가 뭘로 해시가 되어 회원가입이 되었는지 알아야
    //같은 해시로 암호화해서 DB에 있는 해시랑 비교할 수 있음
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //csrf 토큰 비활성화
        http
                .authorizeRequests()
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/");// spring-security가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
    }
}
