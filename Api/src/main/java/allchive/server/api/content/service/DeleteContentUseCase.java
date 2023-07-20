package allchive.server.api.content.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.mapper.RecycleMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.validator.ContentValidator;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteContentUseCase {
    private final ContentValidator contentValidator;
    private final ContentDomainService contentDomainService;
    private final RecycleMapper recycleMapper;
    private final RecycleDomainService recycleDomainService;

    @Transactional
    public void execute(Long contentId) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(contentId, userId);
        contentDomainService.softDeleteById(contentId);
        createRecycle(userId, contentId);
    }

    private void validateExecution(Long contentId, Long userId) {
        contentValidator.verifyUser(contentId, userId);
    }

    private void createRecycle(Long userId, Long contentId) {
        Recycle recycle =
                recycleMapper.toContentRecycleEntity(userId, contentId, RecycleType.CONTENT);
        recycleDomainService.save(recycle);
    }
}
