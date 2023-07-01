package allchive.server.api.auth.model.dto;


import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoUserInfoDto {

    // oauth인증한 사용자 고유 아이디
    private final String oauthId;

    private final String email;
    private final String phoneNumber;
    private final String profileImage;
    private final String name;
    private final OauthProvider oauthProvider;

    public OauthInfo toOauthInfo() {
        return OauthInfo.of(oauthProvider, oauthId);
    }
}
;
