package allchive.server.api.user.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.user.model.dto.response.GetUserProfileResponse;
import allchive.server.api.user.model.mapper.UserMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.validator.UserValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetUserProfileUseCase {
    private final ArchivingAdaptor archivingAdaptor;
    private final UserAdaptor userAdaptor;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    @Transactional(readOnly = true)
    public GetUserProfileResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(userId);
        User user = userAdaptor.findById(userId);
        List<Archiving> archivingList = archivingAdaptor.queryArchivingByUserId(userId);
        return userMapper.toGetUserProfileResponse(archivingList, user);
    }

    private void validateExecution(Long userId) {
        userValidator.validateUserStatusNormal(userId);
    }
}
