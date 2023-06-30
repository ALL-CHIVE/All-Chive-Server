package allchive.server.api.auth.service.helper;


import allchive.server.api.auth.model.dto.response.OauthSignInResponse;
import allchive.server.core.annotation.Helper;
import allchive.server.core.jwt.JwtTokenProvider;
import allchive.server.domain.domains.user.adaptor.RefreshTokenAdaptor;
import allchive.server.domain.domains.user.domain.RefreshTokenEntity;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Helper
@RequiredArgsConstructor
public class TokenGenerateHelper {

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenAdaptor refreshTokenAdaptor;

    @Transactional
    public OauthSignInResponse execute(User user) {
        String newAccessToken =
                jwtTokenProvider.generateAccessToken(user.getId(), UserRole.USER.getValue());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        RefreshTokenEntity newRefreshTokenEntityEntity =
                RefreshTokenEntity.builder()
                        .refreshToken(newRefreshToken)
                        .id(user.getId())
                        .ttl(jwtTokenProvider.getRefreshTokenTTLSecond())
                        .build();
        refreshTokenAdaptor.save(newRefreshTokenEntityEntity);

        return OauthSignInResponse.of(
                true,
                null,
                newAccessToken,
                jwtTokenProvider.getAccessTokenTTLSecond(),
                newRefreshToken,
                jwtTokenProvider.getRefreshTokenTTLSecond());
    }
}
