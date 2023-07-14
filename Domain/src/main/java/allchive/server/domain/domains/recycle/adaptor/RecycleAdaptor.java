package allchive.server.domain.domains.recycle.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.repository.RecycleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class RecycleAdaptor {
    private final RecycleRepository recycleRepository;

    public void save(Recycle recycle) {
        recycleRepository.save(recycle);
    }

    public List<Recycle> queryRecycleByUserIdInArchivingIdListAndContentIdList(List<Long> archivingIds, List<Long> contentIds, Long userId) {
        return recycleRepository
                .queryRecycleByUserIdInArchivingIdListAndContentIdList(archivingIds, contentIds, userId);
    }

    public void deleteAll(List<Recycle> recycleList) {
        recycleRepository.deleteAll(recycleList);
    }

    public List<Recycle> findAllByUserId(Long userId) {
        return recycleRepository.findAllByUserId(userId);
    }
}
