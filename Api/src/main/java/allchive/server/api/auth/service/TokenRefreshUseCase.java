package allchive.server.api.auth.service;

import allchive.server.api.auth.model.dto.response.OauthRegisterResponse;
import allchive.server.api.auth.model.dto.response.OauthSignInResponse;
import allchive.server.api.auth.service.helper.TokenGenerateHelper;
import allchive.server.core.annotation.UseCase;
import allchive.server.core.jwt.JwtTokenProvider;
import allchive.server.domain.domains.user.adaptor.RefreshTokenAdaptor;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.RefreshTokenEntity;
import allchive.server.domain.domains.user.domain.User;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class TokenRefreshUseCase {
    private final RefreshTokenAdaptor refreshTokenAdaptor;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserAdaptor userAdaptor;
    private final TokenGenerateHelper tokenGenerateHelper;

    public OauthRegisterResponse execute(String refreshToken) {
        RefreshTokenEntity oldToken = refreshTokenAdaptor.findTokenByRefreshToken(refreshToken);
        Long userId = jwtTokenProvider.parseRefreshToken(oldToken.getRefreshToken());
        User user = userAdaptor.queryUserById(userId);
        return OauthRegisterResponse.from(tokenGenerateHelper.execute(user));
    }
}
