package ch.ffhs.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * CustomerConfiguration handels the web security for the application
 */
@Configuration
@EnableWebSecurity
public class CustomerConfiguration {

    /**
     * method defines a UserDetailsService bean and
     * is used to call user details which are in need for the authentication
     * of the user
     *
     * @return customerConfigService object
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomerConfigService();
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
    public DaoAuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
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

        http.headers().frameOptions().sameOrigin();
        http.authenticationProvider(provider());
        http.csrf().disable();
        http.cors(withDefaults());
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:9000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("Content-Security-Policy", "Location"));
        configuration.setMaxAge(600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
