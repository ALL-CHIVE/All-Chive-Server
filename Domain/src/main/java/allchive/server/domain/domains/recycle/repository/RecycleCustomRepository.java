package allchive.server.domain.domains.recycle.repository;


import allchive.server.domain.domains.recycle.domain.Recycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecycleCustomRepository {
    List<Recycle> queryRecycleByUserIdInArchivingIdListAndContentIdList(List<Long> archivingIds, List<Long> contentIds, Long userId);
}
