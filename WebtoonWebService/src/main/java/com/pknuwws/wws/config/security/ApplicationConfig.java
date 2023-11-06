package com.pknuwws.wws.config.security;


import com.pknuwws.wws.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig  {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        log.info("userDetailsService");
        return userId -> userRepository.findByLoginId(userId)
                .orElseThrow(()-> new UsernameNotFoundException("user not found = "+userId));
    }
    /**
     * Security 동작에 필요한 인증 제공자, 인증 관리자, 패스워드 인코더를 설정 및 등록한다
     */

    // 인증 제공자 : 인증을 처리하는데 사용하는 authenticationProvider를 생성한다.
    // DaoAuthenticationProvider는 가장 일반적인 인증 제공자 형태로, UserDetailsService에서 제공받은 사용자 이름과 비밀번호를 기반으로 인증을 수행한다
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    // 인증 관리자 : 인증을 관리하는데 필요한 authenticationManager를 생성한다.
    // authenticationConfiguration에서 현재 컨텍스트에 등록된 인증관리자를 가져와서 return한다
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }


    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
