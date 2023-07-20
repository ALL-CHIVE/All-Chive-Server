package allchive.server.api.recycle.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.dto.response.DeletedObjectResponse;
import allchive.server.api.recycle.model.mapper.RecycleMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.adaptor.ContentTagGroupAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.recycle.adaptor.RecycleAdaptor;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetDeletedObjectUseCase {
    private final RecycleAdaptor recycleAdaptor;
    private final ArchivingAdaptor archivingAdaptor;
    private final ContentAdaptor contentAdaptor;
    private final RecycleMapper recycleMapper;
    private final ContentTagGroupAdaptor contentTagGroupAdaptor;

    @Transactional(readOnly = true)
    public DeletedObjectResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Recycle> recycles = recycleAdaptor.findAllByUserId(userId);
        List<Long> archivingIds =
                recycles.stream()
                        .filter(recycle -> recycle.getRecycleType().equals(RecycleType.ARCHIVING))
                        .map(Recycle::getArchivingId)
                        .toList();
        List<Long> contentIds =
                recycles.stream()
                        .filter(recycle -> recycle.getRecycleType().equals(RecycleType.CONTENT))
                        .map(Recycle::getContentId)
                        .toList();
        List<Archiving> archivings = archivingAdaptor.findAllByIdIn(archivingIds);
        List<Content> contents = contentAdaptor.findAllByIdIn(contentIds);
        List<ContentTagGroup> contentTagGroups =
                contentTagGroupAdaptor.queryContentTagGroupByContentIn(contents);
        return recycleMapper.toDeletedObjectResponse(
                archivings, userId, contents, contentTagGroups);
    }
}
