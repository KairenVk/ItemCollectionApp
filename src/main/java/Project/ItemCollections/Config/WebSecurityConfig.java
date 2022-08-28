package Project.ItemCollections.Config;

import Project.ItemCollections.Entities.Collection.Collection;
import Project.ItemCollections.Entities.User.User;
import Project.ItemCollections.Repositories.CollectionRepository;
import Project.ItemCollections.Repositories.UserRepository;
import Project.ItemCollections.Services.AppUserDetailsService;
import Project.ItemCollections.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private AuthService authService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin")
                    .hasAuthority("ROLE_ADMIN")
                    .and()
                .authorizeRequests()
                    .antMatchers("/collections", "/collection/create")
                    .hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests()
                    .antMatchers("collection/{id}/createItem", "collection/{id}/edit", "collection/{id}/delete").access("@AuthService.hasCollectionPermission(#id)")
                    .and()
                .authorizeRequests()
                    .antMatchers("item/{id}/edit", "item/{id}/delete").access("@AuthService.hasItemPermission(#id)")
                    .and()
                .authorizeRequests()
                    .antMatchers("/", "/main", "/registration", "/registerUser", "/search", "/usersCollections", "/collection/{id}/overview", "/item/{id}/overview", "/login*", "/uploads/**").permitAll()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/main", true)
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }
}