package allchive.server.infrastructure.oauth.kakao.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OIDCPublicKeyDto {
    private String kid;
    private String alg;
    private String use;
    private String n;
    private String e;
}
