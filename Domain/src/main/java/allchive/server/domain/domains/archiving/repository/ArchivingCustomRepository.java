package allchive.server.domain.domains.archiving.repository;


import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ArchivingCustomRepository {
    Slice<Archiving> querySliceArchivingExceptBlock(
            List<Long> archivingIdList, List<Long> blockList, Category category, Pageable pageable);

    Slice<Archiving> querySliceArchivingByUserId(Long userId, Category category, Pageable pageable);

    Slice<Archiving> querySliceArchivingIn(
            List<Long> archivingIdList, Category category, Pageable pageable);

    List<Archiving> queryArchivingByUserId(Long userId);

    boolean queryArchivingExistById(Long archivingId);
}
