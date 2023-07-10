package allchive.server.api.content.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.response.ContentTagResponse;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.adaptor.ContentTagGroupAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetContentUseCase {
    private final ArchivingValidator archivingValidator;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupAdaptor contentTagGroupAdaptor;
    private final ContentMapper contentMapper;

    @Transactional(readOnly = true)
    public ContentTagResponse execute(Long contentId) {
        Content content = contentAdaptor.findById(contentId);
        Long userId = SecurityUtil.getCurrentUserId();
        archivingValidator.validatePublicStatus(content.getArchivingId(), userId);
        List<ContentTagGroup> contentTagGroupList =
                contentTagGroupAdaptor.findAllByContent(content);
        return contentMapper.toContentTagResponse(content, contentTagGroupList);
    }
}
