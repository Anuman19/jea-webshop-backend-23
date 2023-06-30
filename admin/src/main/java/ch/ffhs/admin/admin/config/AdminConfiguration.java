package ch.ffhs.admin.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * AdminConfiguration handels the web security for the application
 */
@Configuration
@EnableWebSecurity
public class AdminConfiguration {
    /**
     * method defines a UserDetailsService bean and
     * is used to call user details which are in need for the authentication
     * of the admin
     *
     * @return adminServiceConfig object
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new AdminServiceConfig();
    }

    /**
     * method defines a BCryptPasswordEncoder bean and
     * is used to hash and validate the password
     *
     * @return BCryptPasswordEncoder object
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * method defines a DaoAuthenticationProvider bean which is used for
     * the authentication with the UserDetailsService and PasswordEncoder
     *
     * @return DaoAuthenticationProvider object
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * method defines a safety filter chain for the HTTP requests
     *
     * @param http HttpSecurity parameter
     * @return DaoAuthenticationProvider object
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /** http.authorizeHttpRequests().requestMatchers("/*").permitAll()
         .requestMatchers("/admin/*").hasAuthority("ADMIN").and().formLogin().loginPage("/login").loginProcessingUrl("/do-login").defaultSuccessUrl("/index").failureForwardUrl("/login?error").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
         **/
        http.headers().frameOptions().sameOrigin();
        http.authenticationProvider(daoAuthenticationProvider());
        http.csrf((csrf) -> csrf.disable());
        http.cors(withDefaults());
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:9000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("Content-Security-Policy", "Location"));
        configuration.setMaxAge(600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
