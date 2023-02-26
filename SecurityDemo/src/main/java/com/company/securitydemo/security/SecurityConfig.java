package com.company.securitydemo.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;
import org.springframework.security.web.util.matcher.*;

import java.util.concurrent.*;

import static com.company.securitydemo.security.UserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);

        return provider;
   }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .csrf (csrf->csrf.disable())
                .authorizeRequests(
                        auth -> {
                            auth.antMatchers("/", "css/*", "js/*", "index").permitAll();
                            auth.antMatchers("/api/**").hasRole(STUDENT.name());
                            auth.anyRequest().authenticated();
                        })
                .formLogin(
                        form ->{
                            form.loginPage("/login").permitAll();
                            form.defaultSuccessUrl("/courses");
                            form.usernameParameter("username");
                            form.passwordParameter("password");
                        }
                )
                .rememberMe(
                        remember ->{
                            remember.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(15));
                            remember.key("keyToHashTheContent");
                            remember.rememberMeParameter("remember-me");
                        }
                )
                .logout(
                        logout->{
                            logout.logoutUrl("/logout");
                            logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
                            logout.clearAuthentication(true);
                            logout.invalidateHttpSession(true);
                            logout.deleteCookies("JSESSIONID", "remember-me");
                            logout.logoutSuccessUrl("/login");
                        }
                )
                .build();
    }

}
