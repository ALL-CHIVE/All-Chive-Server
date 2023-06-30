package allchive.server.core.common;


import allchive.server.core.jwt.JwtProperties;
import allchive.server.core.properties.KakaoOAuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({
    JwtProperties.class,
    KakaoOAuthProperties.class
})
@Configuration
public class CoreConfigurationPropertiesConfig {}
