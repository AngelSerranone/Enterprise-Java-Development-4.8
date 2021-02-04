package com.example.Lab4_8.security;
import com.example.Lab4_8.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Con este metodo estamos cogiendo los usuarios y sus roles de la bbdd
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder.encode("123456")).roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password(passwordEncoder.encode("123456")).roles("USER");
    }

    // Con este método defino que roles han de tener para acceder a ñas distintas urls.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable().authorizeRequests()
                .mvcMatchers("/post-and-author/**").authenticated()
                .mvcMatchers("/new-post").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .mvcMatchers("/new-author").hasRole("ADMIN")
                .mvcMatchers("/update-info/**").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .mvcMatchers("/update-author/**").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .mvcMatchers("/delete-post/**").hasRole("ADMIN")
                .mvcMatchers("/delete-author/**").hasRole("ADMIN")
                .anyRequest().permitAll();
    }
}