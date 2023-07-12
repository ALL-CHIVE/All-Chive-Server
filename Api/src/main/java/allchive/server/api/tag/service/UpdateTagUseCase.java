package allchive.server.api.tag.service;

import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.tag.model.dto.request.UpdateTagRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
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
    public void execute(Long tagId, UpdateTagRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        tagValidator.verifyUser(tagId, userId);
        tagDomainService.updateTag(tagId, request.getName());
    }
}
