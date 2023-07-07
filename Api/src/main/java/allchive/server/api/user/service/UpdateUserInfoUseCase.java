package allchive.server.api.user.service;


import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.user.model.dto.request.UpdateUserInfoRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.service.UserDomainService;
import allchive.server.domain.domains.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateUserInfoUseCase {
    private final UserDomainService userDomainService;
    private final UserValidator userValidator;

    @Transactional
    public void execute(UpdateUserInfoRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        userValidator.validateUserStatusNormal(userId);
        String imgKey = UrlUtil.convertUrlToKey(request.getImgUrl());
        userDomainService.updateUserInfo(
                userId, request.getName(), request.getEmail(), request.getNickname(), imgKey);
    }
}
