package allchive.server.api.auth.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.adaptor.RefreshTokenAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class LogOutUserUseCase {
    private final RefreshTokenAdaptor refreshTokenAdaptor;

    @Transactional
    public void execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        refreshTokenAdaptor.deleteTokenByUserId(userId);
    }
}
