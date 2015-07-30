package umedia.test.oauth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//user store?
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("marissa").password("koala").roles("USER").and().withUser("paul")
                .password("emu").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/images/**", "/oauth/uncache_approvals", "/oauth/cache_approvals");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //how user login rule, authorities
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
                 http
            .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().hasRole("USER")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/login.jsp?authorization_error=true")
                .and()
            // TODO: put CSRF protection back into this endpoint
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
            .logout()
            	.logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
            .formLogin()
            	.loginProcessingUrl("/login")
                .failureUrl("/login.jsp?authentication_error=true")
                .loginPage("/login");
        // @formatter:on
    }
}