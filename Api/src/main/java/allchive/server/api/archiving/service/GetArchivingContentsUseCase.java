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
import allchive.server.domain.domains.content.domain.enums.ContentType;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GetArchivingContentsUseCase {
    private final ArchivingAdaptor archivingAdaptor;
    private final ArchivingValidator archivingValidator;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupAdaptor contentTagGroupAdaptor;
    private final ContentMapper contentMapper;
    private final UserAdaptor userAdaptor;
    private final ScrapAdaptor scrapAdaptor;

    @Transactional(readOnly = true)
    public ArchivingContentsResponse execute(
            Long archivingId, Pageable pageable, ContentType contentType, List<Long> tagIds) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(archivingId, userId);
        Archiving archiving = archivingAdaptor.findById(archivingId);
        User owner = userAdaptor.findById(archiving.getUserId());
        Slice<ContentResponse> contentResponseSlice =
                getContentResponseSlice(archivingId, pageable, contentType, tagIds);
        return ArchivingContentsResponse.of(
                SliceResponse.of(contentResponseSlice),
                archiving,
                owner,
                calculateIsMine(archiving, userId),
                calculateIsScrap(archiving, userId));
    }

    private void validateExecution(Long archivingId, Long userId) {
        archivingValidator.validatePublic(archivingId, userId);
        archivingValidator.validateNotDeleteExceptUser(archivingId, userId);
    }

    private Slice<ContentResponse> getContentResponseSlice(
            Long archivingId, Pageable pageable, ContentType contentType, List<Long> tagIds) {
        List<Long> contentIds = null;
        if (tagIds != null) {
            contentIds =
                    contentTagGroupAdaptor.queryContentTagGroupByTagIdInWithContent(tagIds).stream()
                            .map(contentTagGroup -> contentTagGroup.getContent().getId())
                            .toList();
        }
        Slice<Content> contentList =
                contentAdaptor.querySliceContentByArchivingIdAndContentTypeAndIdIn(
                        archivingId, pageable, contentType, contentIds);
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

    private Boolean calculateIsScrap(Archiving archiving, Long userId) {
        User user = userAdaptor.findById(userId);
        if (scrapAdaptor.existsByUserAndArchivingId(user, archiving.getId())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
