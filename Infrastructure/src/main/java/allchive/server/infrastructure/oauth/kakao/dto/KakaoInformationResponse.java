package allchive.server.infrastructure.oauth.kakao.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoInformationResponse {

    private Properties properties;
    private String id;
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @JsonNaming(SnakeCaseStrategy.class)
    public static class Properties {
        private String nickname;
    }

    @Getter
    @NoArgsConstructor
    @JsonNaming(SnakeCaseStrategy.class)
    public static class KakaoAccount {
        private String email;
    }

    public String getId() {
        return id;
    }

    public String getNickName() {
        return properties.getNickname();
    }

    public String getEmail() {
        return kakaoAccount.getEmail();
    }
}
