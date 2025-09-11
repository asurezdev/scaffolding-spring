package co.com.asuarezr.products_msvc.products.infrastructure.configurations.securityConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
  @Value("#{'${cors.allowedOrigins}'.split(',')}")
  private List<String> allowedOrigins;

  @Value("#{'${cors.allowedMethods}'.split(',')}")
  private List<String> allowedMethods;

  @Value("#{'${cors.allowedHeaders}'.split(',')}")
  private List<String> allowedHeaders;

  private List<String> removeWhiteSpace(List<String> list){
    return list.stream().map(String::trim).toList();
  }


  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(removeWhiteSpace(allowedOrigins));
    configuration.setAllowedMethods(removeWhiteSpace(allowedMethods));
    configuration.setAllowedHeaders(removeWhiteSpace(allowedHeaders));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
