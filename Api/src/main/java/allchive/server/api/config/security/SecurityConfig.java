package allchive.server.api.config.security;

import static allchive.server.core.consts.AllchiveConst.SwaggerPatterns;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final FilterConfig filterConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable().cors().and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().expressionHandler(expressionHandler());

        http.authorizeRequests()
                .antMatchers(SwaggerPatterns)
                .permitAll()
                .mvcMatchers("/auth/oauth/**")
                .permitAll()
                .mvcMatchers("/auth/token/refresh")
                .permitAll()
                .mvcMatchers("/**/health/**")
                .permitAll()
                .anyRequest()
                .hasRole("USER");

        http.apply(filterConfig);

        return http.build();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        return new DefaultWebSecurityExpressionHandler();
    }
}
