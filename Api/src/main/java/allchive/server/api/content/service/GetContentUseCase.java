package allchive.server.api.content.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.response.ContentTagResponse;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
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
    private final ArchivingAdaptor archivingAdaptor;

    @Transactional(readOnly = true)
    public ContentTagResponse execute(Long contentId) {
        Long userId = SecurityUtil.getCurrentUserId();
        Content content = contentAdaptor.findById(contentId);
        validateExecution(content.getArchivingId(), userId);
        List<ContentTagGroup> contentTagGroupList =
                contentTagGroupAdaptor.queryContentTagGroupByContentWithTag(content);
        Boolean isMine = calculateIsMine(content.getArchivingId(), userId);
        return contentMapper.toContentTagResponse(content, contentTagGroupList, isMine);
    }

    private void validateExecution(Long archivingId, Long userId) {
        archivingValidator.validatePublicStatus(archivingId, userId);
    }

    private Boolean calculateIsMine(Long archivingId, Long userId) {
        Archiving archiving = archivingAdaptor.findById(archivingId);
        if (archiving.getUserId().equals(userId)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
