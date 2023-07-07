package allchive.server.api.user.service;

import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.user.model.dto.request.UpdateUserInfoRequest;
import allchive.server.api.user.model.dto.response.GetUserInfoResponse;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateUserInfoUseCase {
    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;


    public void execute(UpdateUserInfoRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        String imgKey = UrlUtil.convertUrlToKey(request.getImgUrl());
        userDomainService.updateUserInfo(userId, request.getName(), request.getEmail(), request.getNickname(), imgKey);
    }
}
