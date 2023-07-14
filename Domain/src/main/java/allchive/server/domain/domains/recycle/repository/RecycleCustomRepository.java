package allchive.server.domain.domains.recycle.repository;


import allchive.server.domain.domains.recycle.domain.Recycle;
import java.util.List;

public interface RecycleCustomRepository {
    List<Recycle> queryRecycleByUserIdInArchivingIdListAndContentIdList(
            List<Long> archivingIds, List<Long> contentIds, Long userId);
}
