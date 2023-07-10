package allchive.server.domain.domains.archiving.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.core.error.exception.InternalServerError;
import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Subject;
import allchive.server.domain.domains.archiving.exception.exceptions.ArchivingNotFoundException;
import allchive.server.domain.domains.archiving.repository.ArchivingRepository;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Adaptor
@RequiredArgsConstructor
public class ArchivingAdaptor {
    private final ArchivingRepository archivingRepository;

    public void save(Archiving archiving) {
        if (archiving.getSubject().equals(Subject.ALL)) {
            throw InternalServerError.EXCEPTION;
        }
        archivingRepository.save(archiving);
    }

    public Archiving findById(Long archivingId) {
        return archivingRepository
                .findById(archivingId)
                .orElseThrow(() -> ArchivingNotFoundException.EXCEPTION);
    }

    public void deleteById(Long archivingId) {
        archivingRepository.deleteById(archivingId);
    }

    public Slice<Archiving> querySliceArchivingExceptBlock(
            List<Long> archivingIdList, List<Long> blockList, Subject subject, Pageable pageable) {
        return archivingRepository.querySliceArchivingExceptBlock(
                archivingIdList, blockList, subject, pageable);
    }

    public Slice<Archiving> querySliceArchivingByUserId(
            Long userId, Subject subject, Pageable pageable) {
        return archivingRepository.querySliceArchivingByUserId(userId, subject, pageable);
    }

    public Slice<Archiving> querySliceArchivingIn(
            List<Long> archivingIdList, Subject subject, Pageable pageable) {
        return archivingRepository.querySliceArchivingIn(archivingIdList, subject, pageable);
    }

    public List<Archiving> queryArchivingByUserId(Long userId) {
        return archivingRepository.queryArchivingByUserId(userId);
    }

    public boolean queryArchivingExist(Long archivingId) {
        return archivingRepository.queryArchivingExist(archivingId);
    }

    public List<Archiving> findAllByUserId(Long userId) {
        return archivingRepository.findAllByUserId(userId);
    }
}
