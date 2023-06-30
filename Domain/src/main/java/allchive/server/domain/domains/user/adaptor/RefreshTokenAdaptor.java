package allchive.server.domain.domains.user.adaptor;


import allchive.server.core.annotation.Adaptor;
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
}
