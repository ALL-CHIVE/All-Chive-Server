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
        userValidator.validateUserStatusNormal(userId);
        User user = userAdaptor.queryUserById(userId);
        List<Archiving> archivingList = archivingAdaptor.findAllByUserId(userId);
        return userMapper.toGetUserProfileResponse(archivingList, user);
    }
}
