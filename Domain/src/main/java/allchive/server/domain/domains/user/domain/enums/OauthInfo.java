package allchive.server.domain.domains.user.domain.enums;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private OauthInfo(OauthProvider provider, String oid) {
        this.provider = provider;
        this.oid = oid;
    }

    public static OauthInfo of(OauthProvider provider, String oid) {
        return OauthInfo.builder().provider(provider).oid(oid).build();
    }

    public void withDrawOauthInfo() {
        this.oid = null;
    }
}
