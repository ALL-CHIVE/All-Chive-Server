package allchive.server.domain.domains.archiving.repository;


import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Subject;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ArchivingCustomRepository {
    Slice<Archiving> querySliceArchivingExceptBlock(
            List<Long> archivingIdList, List<Long> blockList, Subject subject, Pageable pageable);

    Slice<Archiving> querySliceArchivingByUserId(Long userId, Subject subject, Pageable pageable);

    Slice<Archiving> querySliceArchivingIn(
            List<Long> archivingIdList, Subject subject, Pageable pageable);

    List<Archiving> queryArchivingByUserId(Long userId);

    boolean queryArchivingExist(Long archivingId);
}
