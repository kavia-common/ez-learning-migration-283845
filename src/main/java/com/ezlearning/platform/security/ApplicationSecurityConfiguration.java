package com.ezlearning.platform.security;

import com.ezlearning.platform.auth.EzLearningUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Application security configuration for Spring Security 6 (Spring Boot 3).
 * Configures authentication using DaoAuthenticationProvider and secures application endpoints.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration {

    /**
     * PUBLIC_INTERFACE
     * Password encoder used by DaoAuthenticationProvider.
     * @return PasswordEncoder instance (BCrypt with strength 11)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    /**
     * PUBLIC_INTERFACE
     * Maps authorities to uppercase and sets default authority to USER.
     * @return a GrantedAuthoritiesMapper for normalizing authorities
     */
    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority("USER");
        return authorityMapper;
    }

    /**
     * PUBLIC_INTERFACE
     * Authentication provider that uses our UserDetailsService and a BCrypt password encoder.
     * @param userDetailsService the custom user details service
     * @param passwordEncoder the encoder
     * @param authoritiesMapper the mapper that normalizes authorities
     * @return a fully configured DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            EzLearningUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder,
            GrantedAuthoritiesMapper authoritiesMapper
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        provider.setAuthoritiesMapper(authoritiesMapper);
        return provider;
    }

    /**
     * PUBLIC_INTERFACE
     * Main Spring Security filter chain.
     * - Disables CSRF and frame options for H2 console support in dev.
     * - Permits access to public pages, static assets and H2 console.
     * - Secures all other endpoints and configures form login/logout.
     *
     * @param http the HttpSecurity builder
     * @param authenticationProvider the DAO authentication provider
     * @return the built SecurityFilterChain
     * @throws Exception if any configuration error occurs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   DaoAuthenticationProvider authenticationProvider) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/discover", "/cursos", "/h2-console/**", "/api/**",
                                 "/register", "/css/**", "/js/**", "/img/**")
                .permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login").permitAll()
            )
            .logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success")
                .permitAll()
            )
            .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
