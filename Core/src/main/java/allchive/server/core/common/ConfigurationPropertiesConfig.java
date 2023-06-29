package allchive.server.core.common;


import allchive.server.core.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({
    JwtProperties.class,
})
@Configuration
public class ConfigurationPropertiesConfig {}
