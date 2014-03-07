package net.nemerosa.iteach.ui.config;

import net.nemerosa.iteach.common.SecurityRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * By default, all queries are accessible anonymously. Security is enforced at service level.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Default
        http.antMatcher("/api/**")
                .httpBasic().realmName("iteach").and()
                .authorizeRequests().anyRequest().permitAll()
        ;
        // Admin for the test API
        http.authorizeRequests().antMatchers("/api/test/**").hasRole(SecurityRoles.ADMIN.substring(5));
        // Registration (no CSRF)
        http.antMatcher("/api/account/register").csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager);
    }

}