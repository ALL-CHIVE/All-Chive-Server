package allchive.server.domain.domains.user.domain.enums;


import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthInfo {

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;

    private String oid;

    @Builder
    public OauthInfo(OauthProvider provider, String oid) {
        this.provider = provider;
        this.oid = oid;
    }

    public OauthInfo withDrawOauthInfo() {
        return OauthInfo.builder()
                .oid(oid)
                .provider(provider)
                .build();
    }
}
