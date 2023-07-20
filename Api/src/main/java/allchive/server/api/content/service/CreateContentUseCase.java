package allchive.server.api.content.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.content.validator.TagValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateContentUseCase {
    private final ArchivingValidator archivingValidator;
    private final ContentMapper contentMapper;
    private final ContentDomainService contentDomainService;
    private final TagValidator tagValidator;
    private final TagAdaptor tagAdaptor;
    private final ContentTagGroupDomainService contentTagGroupDomainService;

    @Transactional
    public void execute(CreateContentRequest request) {
        validateExecution(request);
        List<Tag> tags = tagAdaptor.queryTagByTagIdIn(request.getTagIds());
        Content content = contentMapper.toEntity(request);
        List<ContentTagGroup> contentTagGroupList =
                contentMapper.toContentTagGroupEntityList(content, tags);
        contentTagGroupDomainService.saveAll(contentTagGroupList);
        contentDomainService.save(content);
    }

    private void validateExecution(CreateContentRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        archivingValidator.validateExistById(request.getArchivingId());
        archivingValidator.validateArchivingUser(request.getArchivingId(), userId);
        tagValidator.validateExistTagsAndUser(request.getTagIds(), userId);

    }
}
