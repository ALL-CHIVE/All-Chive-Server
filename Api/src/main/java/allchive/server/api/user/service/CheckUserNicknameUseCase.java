package allchive.server.api.user.service;

import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.user.model.dto.request.CheckUserNicknameRequest;
import allchive.server.api.user.model.dto.request.UpdateUserInfoRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CheckUserNicknameUseCase {
    private final UserDomainService userDomainService;

    public void execute(CheckUserNicknameRequest request) {
        userDomainService.checkUserNickname(request.getNickname());
    }
}
