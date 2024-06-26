package allchive.server.domain.domains.archiving.repository;


import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ArchivingCustomRepository {
    Slice<Archiving> querySliceArchivingExceptBlock(
            List<Long> archivingIdList, List<Long> blockList, Category category, Pageable pageable);

    Slice<Archiving> querySliceArchivingByUserId(Long userId, Category category, Pageable pageable);

    Slice<Archiving> querySliceArchivingByIdInExceptBlockList(
            List<Long> archivingIdList, List<Long> blockList, Category category, Pageable pageable);

    List<Archiving> queryArchivingByUserId(Long userId);

    boolean queryArchivingExistById(Long archivingId);

    Page<Archiving> querySliceArchivingByUserIdAndKeywordsOrderByTagArchvingIds(
            Long userId, String keyword, Pageable pageable, Set<Long> tagArchivingIds);

    Page<Archiving> querySliceArchivingByKeywordExceptBlockOrderByTagArchvingIds(
            List<Long> archivingIdList,
            List<Long> blockList,
            String keyword,
            Pageable pageable,
            Set<Long> tagArchivingIds,
            Long userId);

    List<Archiving> queryArchivingOrderByScrapCntLimit5ExceptBlockList(List<Long> blockList);

    Slice<Archiving> querySliceArchivingByPublicStatus(Pageable pageable, Boolean publicStatus);
}
