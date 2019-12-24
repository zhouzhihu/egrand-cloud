package com.egrand.cloud.oauth.server.config;

import com.egrand.cloud.oauth.server.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//注解开启在方法上的保护功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserServiceDetail userServiceDetail;

//    @Value("${spring.sercurity.white-url:/oauth/**,/authentication/**,/oauthLogin,/static/**,/login/**}")
//    private String[] whiteUri;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.cors();
//        Set<String> set = new HashSet<>(Arrays.asList(this.whiteUri));
//        set.add("/oauth/**");
//        set.add("/authentication/**");
//        String[] uri = set.toArray(new String[0]);
//        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http.authorizeRequests().antMatchers(HttpMethod.GET, uri)).permitAll();
//        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http.authorizeRequests().antMatchers(HttpMethod.POST, uri)).permitAll();
//        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, new String[]{"/oauth/**", "/authentication/**"})).permitAll();
//        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http.authorizeRequests().anyRequest()).authenticated();
//        ((FormLoginConfigurer) ((FormLoginConfigurer) http.formLogin()
//                .loginPage("/authentication/require")
//                .successHandler((AuthenticationSuccessHandler) new ForwardAuthenticationSuccessHandler("/users/current")))
//                .failureUrl("/oauth/approvale/error"))
//                .loginProcessingUrl("/authentication/form");
////        http.authorizeRequests().accessDecisionManager(accessDecisionManager());
////        http
////                .authorizeRequests().anyRequest().authenticated()
////                .and()
////                .csrf().disable();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/error",
                "/static/**",
                "/v2/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/favicon.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceDetail).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}