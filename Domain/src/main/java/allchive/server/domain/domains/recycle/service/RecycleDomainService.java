package allchive.server.domain.domains.recycle.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.recycle.adaptor.RecycleAdaptor;
import allchive.server.domain.domains.recycle.domain.Recycle;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class RecycleDomainService {
    private final RecycleAdaptor recycleAdaptor;

    public void save(Recycle recycle) {
        recycleAdaptor.save(recycle);
    }

    public void deleteAllByUserIdAndArchivingIdOrUserIdAndContentId(List<Long> archivingIds, List<Long> contentIds, Long userId) {
        List<Recycle> recycleList = recycleAdaptor
                .queryRecycleByUserIdInArchivingIdListAndContentIdList(archivingIds, contentIds, userId);
        recycleAdaptor.deleteAll(recycleList);
    }
}
