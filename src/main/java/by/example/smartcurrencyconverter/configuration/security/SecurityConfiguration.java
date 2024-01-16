package by.example.smartcurrencyconverter.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Component
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtTokenFilter JwtTokenFilter;
    private final static String H2_URL_PATTERN = "/h2/*";


    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity,
                                                      HandlerMappingIntrospector introspector) throws Exception{

        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        RequestMatcher myMatcher = new AntPathRequestMatcher(H2_URL_PATTERN);
        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(mvcMatcherBuilder.pattern("/user/registration")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/user/login")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/user/signin")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/converter")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/css/*")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/navbar/*")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/error")).permitAll()
                        .requestMatchers(myMatcher).authenticated()
                        .anyRequest().authenticated()
                        )
                .addFilterBefore(JwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin((form) -> form.loginPage("/user/login")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .permitAll())

                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

   


return httpSecurity.build();

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
