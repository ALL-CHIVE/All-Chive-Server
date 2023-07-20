package allchive.server.api.user.service;


import allchive.server.api.user.model.dto.request.CheckUserNicknameRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CheckUserNicknameUseCase {
    private final UserDomainService userDomainService;

    @Transactional(readOnly = true)
    public void execute(CheckUserNicknameRequest request) {
        userDomainService.checkUserNickname(request.getNickname());
    }
}
