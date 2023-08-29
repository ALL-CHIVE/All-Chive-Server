package allchive.server.api.user.service;


import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.user.model.dto.request.UpdateUserInfoRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.core.event.Event;
import allchive.server.core.event.events.s3.S3ImageDeleteEvent;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.service.UserDomainService;
import allchive.server.domain.domains.user.validator.UserValidator;
import allchive.server.infrastructure.s3.service.S3DeleteObjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateUserInfoUseCase {
    private final UserDomainService userDomainService;
    private final UserValidator userValidator;
    private final UserAdaptor userAdaptor;
    private final S3DeleteObjectService s3DeleteObjectService;

    @Transactional
    @DistributedLock(lockType = DistributedLockType.USER, identifier ={"userId"})
    public void execute(UpdateUserInfoRequest request, Long userId) {
        validateExecution(userId);
        eliminateOldImage(userId, request.getImgUrl());
        userDomainService.updateUserInfo(
                userId,
                request.getName(),
                request.getEmail(),
                request.getNickname(),
                UrlUtil.convertUrlToKey(request.getImgUrl()));
    }

    private void validateExecution(Long userId) {
        userValidator.validateUserStatusNormal(userId);
    }

    private void eliminateOldImage(Long userId, String newUrl) {
        User user = userAdaptor.findById(userId);
        if (UrlUtil.validateS3Key(user.getProfileImgUrl())
                && !user.getProfileImgUrl().equals(UrlUtil.convertUrlToKey(newUrl))) {
            Event.raise(S3ImageDeleteEvent.from(List.of(user.getProfileImgUrl())));
        }
    }
}
