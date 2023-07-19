package allchive.server.api.user.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.user.model.dto.response.GetUserInfoResponse;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetUserInfoUseCase {
    private final UserAdaptor userAdaptor;
    private final UserValidator userValidator;

    @Transactional(readOnly = true)
    public GetUserInfoResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        userValidator.validateUserStatusNormal(userId);
        User user = userAdaptor.findUserById(userId);
        return GetUserInfoResponse.from(user);
    }
}
