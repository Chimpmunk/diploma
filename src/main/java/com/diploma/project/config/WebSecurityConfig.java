package com.diploma.project.config;

import com.diploma.project.models.UserPrincipalDetaillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserPrincipalDetaillsService userPrincipalDetaillsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPrincipalDetaillsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/search").permitAll()
                .antMatchers("/login").not().authenticated()
                .antMatchers("/registration").not().authenticated()
                .antMatchers("/profile", "/device/review/**").authenticated()
                .antMatchers("/add").hasRole("ADMIN")
                .antMatchers("/device/edit/**").hasRole("ADMIN")
                .antMatchers("/device/delete/**").hasRole("ADMIN")
                .antMatchers("/device/add-to-basket/**").authenticated()
                .antMatchers("/basket").authenticated()
                .antMatchers("/basket-clear").authenticated()
                .antMatchers("/order").authenticated()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .rememberMe();

    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
