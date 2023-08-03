package allchive.server.api.content.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.response.ContentTagInfoResponse;
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
import allchive.server.domain.domains.content.validator.ContentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetContentInfoUseCase {
    private final ContentValidator contentValidator;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupAdaptor contentTagGroupAdaptor;
    private final ContentMapper contentMapper;
    private final ArchivingAdaptor archivingAdaptor;

    @Transactional(readOnly = true)
    public ContentTagInfoResponse execute(Long contentId) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(contentId, userId);
        Content content = contentAdaptor.findById(contentId);
        Archiving archiving = archivingAdaptor.findById(content.getArchivingId());
        List<ContentTagGroup> contentTagGroupList =
                contentTagGroupAdaptor.queryContentTagGroupByContentWithTag(content);
        Boolean isMine = calculateIsMine(content.getArchivingId(), userId);
        return contentMapper.toContentTagInfoResponse(archiving, content, contentTagGroupList, isMine);
    }

    private void validateExecution(Long contentId, Long userId) {
        contentValidator.validatePublic(contentId, userId);
        contentValidator.verifyUser(contentId, userId);
    }

    private Boolean calculateIsMine(Long archivingId, Long userId) {
        Archiving archiving = archivingAdaptor.findById(archivingId);
        if (archiving.getUserId().equals(userId)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
