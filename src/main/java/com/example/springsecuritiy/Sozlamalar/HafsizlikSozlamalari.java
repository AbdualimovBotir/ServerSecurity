package com.example.springsecuritiy.Sozlamalar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class HafsizlikSozlamalari extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/springSecurity/add/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/springSecurity/*").hasAnyRole("ADMIN","SUPERUSER")
                .antMatchers(HttpMethod.GET,"/springSecurity/*").hasAnyRole("ADMIN","SUPERUSER","USER")
                .antMatchers(HttpMethod.PUT,"/springSecurity/*").hasAnyRole("ADMIN","SUPERUSER")
                .antMatchers(HttpMethod.DELETE,"/springSecurity/*").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("Admin").password(passwordEncoder().encode("qwerty1")).roles("ADMIN").authorities("ADDUSER","READALLUSER","READIDUSER","WRITEIDUSER","DELETEUSER")
                .and()
                .withUser("SuperUser").password(passwordEncoder().encode("qwerty2")).roles("SUPERUSER").authorities("WRITEIDUSER","READALLUSER","READIDUSER")
                .and()
                .withUser("User").password(passwordEncoder().encode("qwerty3")).roles("USER").authorities("READIDUSER");
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }
}
