package allchive.server.infrastructure.oauth.apple.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class AppleTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String idToken;
}
