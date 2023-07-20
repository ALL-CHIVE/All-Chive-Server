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
        archivingValidator.validatePublicStatus(archivingId, userId);
        archivingValidator.validateDeleteStatus(archivingId, userId);
        Archiving archiving = archivingAdaptor.findById(archivingId);
        Slice<Content> contentList =
                contentAdaptor.querySliceContentByArchivingId(archivingId, pageable);
        List<ContentTagGroup> contentTagGroupList =
                contentTagGroupAdaptor.queryContentTagGroupByContentIn(contentList.getContent());
        Slice<ContentResponse> contentResponseSlice =
                contentList.map(
                        content -> contentMapper.toContentResponse(content, contentTagGroupList));
        return ArchivingContentsResponse.of(SliceResponse.of(contentResponseSlice), archiving);
    }
}
