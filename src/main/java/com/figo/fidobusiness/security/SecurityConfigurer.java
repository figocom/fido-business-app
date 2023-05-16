package com.figo.fidobusiness.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true

)
@PropertySource("classpath:application.properties")
public class SecurityConfigurer {
    public static final String[] WHITE_LIST = {
            "/css/**",
            "/js/**",
            "/img/**",
            "/error",
            "/auth/login",
            "/auth/register",
            "/",
    };

    private final AuthUserDetailsService authUserDetailsService;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    public SecurityConfigurer( AuthUserDetailsService authUserDetailsService, AuthenticationFailureHandler authenticationFailureHandler) {

        this.authUserDetailsService = authUserDetailsService;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public ConcurrentSessionControlAuthenticationStrategy sessionAuthenticationStrategy() {
        ConcurrentSessionControlAuthenticationStrategy strategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        strategy.setMaximumSessions(1);
        strategy.setExceptionIfMaximumExceeded(false);
        return strategy;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(WHITE_LIST)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/home", false)
                                .failureHandler(authenticationFailureHandler)
                )

                .sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry())
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().newSession()
                .sessionAuthenticationErrorUrl("/login?error=true")
                .sessionAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=true"))
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy())
                .maximumSessions(1)
                .and()
                .and()
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutUrl("/auth/logout")
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID", "rememberME")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))

                )
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/auth/login"))
                .and()
                .userDetailsService(authUserDetailsService)
                .rememberMe(httpSecurityRememberMeConfigurer ->
                        httpSecurityRememberMeConfigurer
                                .rememberMeParameter("rememberMe")
                                .key("EWT$@WEFYG%H$ETGE@R!T#$HJYYT$QGRWHNJU%$TJRUYRHFRYFJRYUYRHD")
                                .tokenValiditySeconds(24 * 60 * 60)// default is 30 minutes
                                .rememberMeCookieName("rememberME")
                                .userDetailsService(authUserDetailsService)
                );
        return http.build();
    }


    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder bCryptPasswordEncoder, AuthUserDetailsService authUserDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }


}
