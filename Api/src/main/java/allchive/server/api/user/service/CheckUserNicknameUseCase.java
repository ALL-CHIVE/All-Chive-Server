package allchive.server.api.user.service;

import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.user.model.dto.request.CheckUserNicknameRequest;
import allchive.server.api.user.model.dto.request.UpdateUserInfoRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.service.UserDomainService;
import allchive.server.domain.domains.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CheckUserNicknameUseCase {
    private final UserDomainService userDomainService;
    private final UserValidator userValidator;

    @Transactional(readOnly = true)
    public void execute(CheckUserNicknameRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        userValidator.validateUserStatusNormal(userId);
        userDomainService.checkUserNickname(request.getNickname());
    }
}
