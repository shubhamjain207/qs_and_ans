package com.qsanspack.qsandans.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
// import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.JwtEncoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.services.JwtAuthenticationEntryPoint;
import com.qsanspack.qsandans.services.JwtAuthenticationFilter;
import com.qsanspack.qsandans.services.UserService;




@Configuration
@EnableWebSecurity
public class SecurityConfig {



          @Bean
          public PasswordEncoder encoder() {

                return new BCryptPasswordEncoder();
          }



         


          @Autowired
          private JwtAuthenticationEntryPoint point;

          @Autowired
          private JwtAuthenticationFilter filter;

          @Autowired
          private UserDetailsService service;

        
        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider(){
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(service);
                provider.setPasswordEncoder(encoder());
                return provider;
        }


          @Bean
          public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception{
                return builder.getAuthenticationManager();
          }

        // @Bean
        // public AuthenticationManager manager(UserDetailsService service) {
        //         DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        //         daoProvider.setUserDetailsService(service);
        //         daoProvider.setPasswordEncoder(encoder());
        //         return new ProviderManager(daoProvider);
        // }
 
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .cors(cors->cors.disable())
                                .authorizeHttpRequests(auth -> {
                                        auth.requestMatchers("/auth/**").permitAll();
                                        auth.requestMatchers("/admin/**").hasRole("ADMIN");
                                        auth.requestMatchers("/users/**").hasAnyRole("ADMIN", "USER");
                                        auth.requestMatchers("/user/register").permitAll();
                                        auth.requestMatchers("/user/createprofilepage").permitAll();
                                        auth.requestMatchers("/user/registerProcess").permitAll();
                                        auth.requestMatchers("/user/home").hasAnyRole("ADMIN", "USER");
                                        auth.requestMatchers("/user/login").permitAll();
                                        auth.requestMatchers("/user/profile").permitAll();
                                        auth.requestMatchers("/styles/**").permitAll();

                                        auth.anyRequest().authenticated();
                                })

                               .exceptionHandling(ex->ex.authenticationEntryPoint(point))

                        
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
                
                return http.build();

        }

        // @Bean
        // public JwtDecoder jwtDecoder() {
        //         return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
        // }

        // @Bean
        // public JwtEncoder jwtEncoder() {
        //         JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        //         JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        //         return new NimbusJwtEncoder(jwks);
        // }

        // @Bean
        // public JwtAuthenticationConverter jwtAuthenticationConverter() {
        //         JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        //         jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        //         jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        //         JwtAuthenticationConverter jwtAuthenticationConverter1 = new JwtAuthenticationConverter();
        //         jwtAuthenticationConverter1.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        //         return jwtAuthenticationConverter1;

        // }

       

}
