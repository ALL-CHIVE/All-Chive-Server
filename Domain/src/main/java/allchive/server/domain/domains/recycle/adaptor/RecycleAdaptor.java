package allchive.server.domain.domains.recycle.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.repository.RecycleRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RecycleAdaptor {
    private final RecycleRepository recycleRepository;

    public void save(Recycle recycle) {
        recycleRepository.save(recycle);
    }

    public List<Recycle> queryRecycleByUserIdAndArchivingIdInOrUserIdAndContentIdIn(
            List<Long> archivingIds, List<Long> contentIds, Long userId) {
        return recycleRepository.queryRecycleByUserIdAndArchivingIdInOrUserIdAndContentIdIn(
                archivingIds, contentIds, userId);
    }

    public void deleteAll(List<Recycle> recycleList) {
        recycleRepository.deleteAll(recycleList);
    }

    public List<Recycle> findAllByUserId(Long userId) {
        return recycleRepository.findAllByUserId(userId);
    }

    public List<Recycle> findAllByDeletedAtBefore(LocalDateTime deleteStandard) {
        return recycleRepository.findAllByDeletedAtBefore(deleteStandard);
    }

    public void deleteAllByUserId(Long userId) {
        recycleRepository.deleteAllByUserId(userId);
    }
}
