package allchive.server.domain.domains.archiving.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.core.error.exception.InternalServerError;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import allchive.server.domain.domains.archiving.exception.exceptions.ArchivingNotFoundException;
import allchive.server.domain.domains.archiving.repository.ArchivingRepository;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Adaptor
@RequiredArgsConstructor
public class ArchivingAdaptor {
    private final ArchivingRepository archivingRepository;

    public void save(Archiving archiving) {
        if (archiving.getCategory().equals(Category.ALL)) {
            throw InternalServerError.EXCEPTION;
        }
        archivingRepository.save(archiving);
    }

    public Archiving findById(Long archivingId) {
        return archivingRepository
                .findById(archivingId)
                .orElseThrow(() -> ArchivingNotFoundException.EXCEPTION);
    }

    public Slice<Archiving> querySliceArchivingExceptBlock(
            List<Long> archivingIdList,
            List<Long> blockList,
            Category category,
            Pageable pageable) {
        return archivingRepository.querySliceArchivingExceptBlock(
                archivingIdList, blockList, category, pageable);
    }

    public Slice<Archiving> querySliceArchivingByUserId(
            Long userId, Category category, Pageable pageable) {
        return archivingRepository.querySliceArchivingByUserId(userId, category, pageable);
    }

    public Slice<Archiving> querySliceArchivingByIdIn(
            List<Long> archivingIdList, Category category, Pageable pageable) {
        return archivingRepository.querySliceArchivingByIdIn(archivingIdList, category, pageable);
    }

    public List<Archiving> queryArchivingByUserId(Long userId) {
        return archivingRepository.queryArchivingByUserId(userId);
    }

    public boolean queryArchivingExist(Long archivingId) {
        return archivingRepository.queryArchivingExistById(archivingId);
    }

    public List<Archiving> findAllByUserId(Long userId) {
        return archivingRepository.findAllByUserId(userId);
    }

    public List<Archiving> findAllByIdIn(List<Long> archivingIdList) {
        return archivingRepository.findAllByIdIn(archivingIdList);
    }

    public void saveAll(List<Archiving> archivings) {
        archivingRepository.saveAll(archivings);
    }

    public void deleteAllById(List<Long> archivingIds) {
        archivingRepository.deleteAllById(archivingIds);
    }

    public Page<Archiving> querySliceArchivingByUserIdAndKeywordsOrderByTagArchvingIds(
            Long userId, String keyword, Pageable pageable, Set<Long> tagArchivingIds) {
        return archivingRepository.querySliceArchivingByUserIdAndKeywordsOrderByTagArchvingIds(
                userId, keyword, pageable, tagArchivingIds);
    }

    public Page<Archiving> querySliceArchivingByKeywordExceptBlockOrderByTagArchvingIds(
            List<Long> archivingIdList,
            List<Long> blockList,
            String keyword,
            Pageable pageable,
            Set<Long> tagArchivingIds,
            Long userId) {
        return archivingRepository.querySliceArchivingByKeywordExceptBlockOrderByTagArchvingIds(
                archivingIdList, blockList, keyword, pageable, tagArchivingIds, userId);
    }

    public List<Archiving> findAllByPublicStatus(Boolean publicStatus) {
        return archivingRepository.findAllByPublicStatus(publicStatus);
    }

    public List<Archiving> queryArchivingOrderByScrapCntLimit5() {
        return archivingRepository.queryArchivingOrderByScrapCntLimit5();
    }
}
