package allchive.server.api.config.security;


import allchive.server.core.helper.SpringEnvironmentHelper;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        if (springEnvironmentHelper.isDevProfile()) {
            allowedOriginPatterns.add("http://localhost:3000");
        }
        allowedOriginPatterns.add("https://www.allchive.co.kr");
        allowedOriginPatterns.add("https://staging.allchive.co.kr");
        String[] patterns = allowedOriginPatterns.toArray(String[]::new);
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOriginPatterns(patterns)
                .exposedHeaders("Set-Cookie")
                .allowCredentials(true);
    }
}
