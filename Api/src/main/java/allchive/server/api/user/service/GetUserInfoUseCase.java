package allchive.server.api.user.service;

import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.user.model.dto.response.GetUserInfoResponse;
import allchive.server.api.user.model.dto.response.GetUserProfileResponse;
import allchive.server.api.user.model.mapper.UserMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetUserInfoUseCase {
    private final UserAdaptor userAdaptor;

    public GetUserInfoResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userAdaptor.queryUserById(userId);
        return GetUserInfoResponse.from(user);
    }
}
