package allchive.server.domain.domains.user.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.core.error.exception.ExpiredRefreshTokenException;
import allchive.server.domain.domains.user.domain.RefreshTokenEntity;
import allchive.server.domain.domains.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RefreshTokenAdaptor {
    private final RefreshTokenRepository refreshTokenRepository;

    public void save(RefreshTokenEntity newRefreshTokenEntityEntity) {
        refreshTokenRepository.save(newRefreshTokenEntityEntity);
    }

    public void deleteTokenByUserId(Long userId) {
        refreshTokenRepository.deleteById(userId.toString());
    }

    public RefreshTokenEntity findTokenByRefreshToken(String refreshToken) {
        return refreshTokenRepository
                .findByRefreshToken(refreshToken)
                .orElseThrow(() -> ExpiredRefreshTokenException.EXCEPTION);
    }

    public void delete(RefreshTokenEntity token) {
        refreshTokenRepository.delete(token);
    }
}
