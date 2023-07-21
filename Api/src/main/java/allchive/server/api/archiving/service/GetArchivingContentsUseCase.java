package allchive.server.api.archiving.service;


import allchive.server.api.archiving.model.dto.response.ArchivingContentsResponse;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.response.ContentResponse;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetArchivingContentsUseCase {
    private final ArchivingAdaptor archivingAdaptor;
    private final ArchivingValidator archivingValidator;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupAdaptor contentTagGroupAdaptor;
    private final ContentMapper contentMapper;

    @Transactional(readOnly = true)
    public ArchivingContentsResponse execute(Long archivingId, Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(archivingId, userId);
        Archiving archiving = archivingAdaptor.findById(archivingId);
        Slice<ContentResponse> contentResponseSlice =
                getContentResponseSlice(archivingId, pageable);
        return ArchivingContentsResponse.of(
                SliceResponse.of(contentResponseSlice),
                archiving,
                calculateIsMine(archiving, userId));
    }

    private void validateExecution(Long archivingId, Long userId) {
        archivingValidator.validatePublicStatus(archivingId, userId);
        archivingValidator.validateDeleteStatus(archivingId, userId);
    }

    private Slice<ContentResponse> getContentResponseSlice(Long archivingId, Pageable pageable) {
        Slice<Content> contentList =
                contentAdaptor.querySliceContentByArchivingId(archivingId, pageable);
        List<ContentTagGroup> contentTagGroupList =
                contentTagGroupAdaptor.queryContentTagGroupByContentIn(contentList.getContent());
        return contentList.map(
                content -> contentMapper.toContentResponse(content, contentTagGroupList));
    }

    private Boolean calculateIsMine(Archiving archiving, Long userId) {
        if (archiving.getUserId().equals(userId)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
