package allchive.server.api.tag.service;

import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.service.ContentTagGroupdDomainService;
import allchive.server.domain.domains.content.service.TagDomainService;
import allchive.server.domain.domains.content.validator.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteTagUseCase {
    private final TagValidator tagValidator;
    private final TagAdaptor tagAdaptor;
    private final ContentTagGroupdDomainService contentTagGroupdDomainService;
    private final TagDomainService tagDomainService;

    @Transactional
    public void execute(Long tagId) {
        Long userId = SecurityUtil.getCurrentUserId();
        tagValidator.verifyUser(tagId, userId);
        Tag tag = tagAdaptor.findById(tagId);
        contentTagGroupdDomainService.deleteById(tag);
        tagDomainService.deleteById(tagId);
    }
}
