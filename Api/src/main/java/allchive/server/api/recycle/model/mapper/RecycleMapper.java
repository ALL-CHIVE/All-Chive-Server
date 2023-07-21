package allchive.server.api.recycle.model.mapper;


import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.content.model.dto.response.ContentResponse;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.api.recycle.model.dto.response.DeletedObjectResponse;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class RecycleMapper {
    private final ContentMapper contentMapper;

    public Recycle toContentRecycleEntity(Long userId, Long contentId, RecycleType type) {
        return Recycle.of(type, contentId, null, userId);
    }

    public Recycle toArchivingRecycleEntity(Long userId, Long archivingId) {
        return Recycle.of(RecycleType.ARCHIVING, null, archivingId, userId);
    }

    public DeletedObjectResponse toDeletedObjectResponse(
            List<Archiving> archivingList,
            Long userId,
            List<Content> contents,
            List<ContentTagGroup> contentTagGroups) {
        List<ArchivingResponse> archivingResponses =
                archivingList.stream()
                        .map(
                                archiving ->
                                        ArchivingResponse.of(
                                                archiving,
                                                archiving.getPinUserId().contains(userId)))
                        .toList();
        List<ContentResponse> contentResponses =
                contents.stream()
                        .map(content -> contentMapper.toContentResponse(content, contentTagGroups))
                        .toList();
        return DeletedObjectResponse.of(archivingResponses, contentResponses);
    }
}
