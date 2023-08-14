package allchive.server.core.properties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties("oauth.apple")
public class AppleOAuthProperties {
    private String baseUrl;
    private String clientId;
    private String webClientId;
    private String keyId;
    private String redirectUrl;
    private String teamId;
    private String webCallbackUrl;
    private String authKey;
}
