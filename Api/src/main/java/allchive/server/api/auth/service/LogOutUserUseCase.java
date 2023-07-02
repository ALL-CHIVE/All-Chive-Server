package allchive.server.api.auth.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.adaptor.RefreshTokenAdaptor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LogOutUserUseCase {
    private final RefreshTokenAdaptor refreshTokenAdaptor;

    public void execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        refreshTokenAdaptor.deleteTokenByUserId(userId);
    }
}
