package allchive.server.api.tag.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.tag.model.dto.request.UpdateTagRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.content.service.TagDomainService;
import allchive.server.domain.domains.content.validator.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateTagUseCase {
    private final TagValidator tagValidator;
    private final TagDomainService tagDomainService;

    @Transactional
    @DistributedLock(lockType = DistributedLockType.TAG, identifier ={"tagId"})
    public void execute(Long tagId, UpdateTagRequest request) {
        validateExecution(tagId);
        tagDomainService.updateTag(tagId, request.getName());
    }

    private void validateExecution(Long tagId) {
        Long userId = SecurityUtil.getCurrentUserId();
        tagValidator.verifyUser(tagId, userId);
    }
}
