package chbbo.BEOhGam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 리소스들이 스프링 시큐리티 필터 규칙에 적용받지 않도록 설정
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/font/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions().disable();

        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/members/**").anonymous()
                .antMatchers("/notes/**").hasRole("USER")
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("userId")
                .passwordParameter("password")

                .and()
                .logout()
                .logoutSuccessUrl("/logout/success")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        // status code 핸들링
        http.exceptionHandling().accessDeniedPage("/denied");

        return http.build();
    }

}
