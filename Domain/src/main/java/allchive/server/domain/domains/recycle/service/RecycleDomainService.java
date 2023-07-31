package allchive.server.domain.domains.recycle.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.recycle.adaptor.RecycleAdaptor;
import allchive.server.domain.domains.recycle.domain.Recycle;
import java.util.List;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class RecycleDomainService {
    private final RecycleAdaptor recycleAdaptor;

    public void save(Recycle recycle) {
        recycleAdaptor.save(recycle);
    }

    public void deleteAllByUserIdAndArchivingIdInOrUserIdAndContentIdIn(
            List<Long> archivingIds, List<Long> contentIds, Long userId) {
        List<Recycle> recycleList =
                recycleAdaptor.queryRecycleByUserIdAndArchivingIdInOrUserIdAndContentIdIn(
                        archivingIds, contentIds, userId);
        recycleAdaptor.deleteAll(recycleList);
    }

    public void deleteAll(List<Recycle> recycles) {
        recycleAdaptor.deleteAll(recycles);
    }

    public void deleteAllByUserId(Long userId) {
        recycleAdaptor.deleteAllByUserId(userId);
    }
}
