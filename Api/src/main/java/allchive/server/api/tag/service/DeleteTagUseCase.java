package allchive.server.api.tag.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.content.service.TagDomainService;
import allchive.server.domain.domains.content.validator.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteTagUseCase {
    private final TagValidator tagValidator;
    private final TagAdaptor tagAdaptor;
    private final ContentTagGroupDomainService contentTagGroupDomainService;
    private final TagDomainService tagDomainService;

    @Transactional
    @DistributedLock(
            lockType = DistributedLockType.TAG,
            identifier = {"tagId"})
    public void execute(Long tagId) {
        validateExecution(tagId);
        Tag tag = tagAdaptor.findById(tagId);
        contentTagGroupDomainService.deleteByTag(tag);
        tagDomainService.deleteById(tagId);
    }

    private void validateExecution(Long tagId) {
        Long userId = SecurityUtil.getCurrentUserId();
        tagValidator.verifyUser(tagId, userId);
    }
}
