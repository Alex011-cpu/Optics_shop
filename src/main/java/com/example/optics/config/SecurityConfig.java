package com.example.optics.config;

import com.example.optics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Класс для конфигурации Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    /**
     * Метод для конфигурации (разрешения для определенных ролей, включение функции запомнить меня и т.д.)
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/registration").not().authenticated()
                .antMatchers("/fontawesome-free/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers(HttpMethod.GET,"/customers/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/customers/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/customers/del/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .failureUrl("/auth/loginError")
                .defaultSuccessUrl("/customer",true)
                .and()
                .rememberMe().key("topSecret");

    }

    /**
     * Метод для шифрофания пароля
     * @return объект класса BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Метод для установки пользовательского сервиса и метода шифрования пароля
     * @param auth
     * @throws Exception
     */
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
