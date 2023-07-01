package allchive.server.api.auth.service.helper;


import allchive.server.api.auth.model.dto.response.OauthSignInResponse;
import allchive.server.core.annotation.Helper;
import allchive.server.core.jwt.JwtTokenProvider;
import allchive.server.domain.domains.user.adaptor.RefreshTokenAdaptor;
import allchive.server.domain.domains.user.domain.RefreshTokenEntity;
import allchive.server.domain.domains.user.domain.User;
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
                jwtTokenProvider.generateAccessToken(user.getId(), user.getUserRole().getValue());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        RefreshTokenEntity newRefreshTokenEntity =
                RefreshTokenEntity.builder()
                        .refreshToken(newRefreshToken)
                        .id(user.getId())
                        .ttl(jwtTokenProvider.getRefreshTokenTTLSecond())
                        .build();
        refreshTokenAdaptor.save(newRefreshTokenEntity);

        return OauthSignInResponse.of(
                true,
                null,
                newAccessToken,
                jwtTokenProvider.getAccessTokenTTLSecond(),
                newRefreshToken,
                jwtTokenProvider.getRefreshTokenTTLSecond());
    }
}
