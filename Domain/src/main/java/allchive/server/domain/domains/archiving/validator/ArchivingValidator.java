package allchive.server.domain.domains.archiving.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.exception.exceptions.ArchivingNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class ArchivingValidator {
    private final ArchivingAdaptor archivingAdaptor;

    public void verifyUser(Long userId, Long archivingId) {
        archivingAdaptor.findById(archivingId).validateUser(userId);
    }

    public void validatePublic(Long archivingId, Long userId) {
        archivingAdaptor.findById(archivingId).validatePublic(userId);
    }

    public void validateNotDeleteExceptUser(Long archivingId, Long userId) {
        archivingAdaptor.findById(archivingId).validateNotDeleteExceptUser(userId);
    }

    public void validateExistById(Long archivingId) {
        if (!archivingAdaptor.queryArchivingExist(archivingId)) {
            throw ArchivingNotFoundException.EXCEPTION;
        }
    }

    public void validateAlreadyPinStatus(Long archivingId, Long userId) {
        archivingAdaptor.findById(archivingId).validateAlreadyPinStatus(userId);
    }

    public void validateNotPinStatus(Long archivingId, Long userId) {
        archivingAdaptor.findById(archivingId).validateNotPinStatus(userId);
    }

    public void validateExistInIdList(List<Long> archivingIdList) {
        List<Archiving> archivingList = archivingAdaptor.findAllByIdIn(archivingIdList);
        if (archivingList.size() != archivingIdList.size()) {
            throw ArchivingNotFoundException.EXCEPTION;
        }
    }

    public void verifyUserInIdList(Long userId, List<Long> archivingIds) {
        List<Archiving> archivingList = archivingAdaptor.findAllByIdIn(archivingIds);
        archivingList.forEach(archiving -> archiving.validateUser(userId));
    }

    public void validateNotDeleted(Long archivingId) {
        archivingAdaptor.findById(archivingId).validateNotDelete();
    }
}
