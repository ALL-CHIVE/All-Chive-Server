package allchive.server.domain.domains.archiving.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.exception.exceptions.AlreadyPinnedArchivingException;
import allchive.server.domain.domains.archiving.exception.exceptions.ArchivingNotFoundException;
import allchive.server.domain.domains.archiving.exception.exceptions.NotPinnedArchivingException;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class ArchivingValidator {
    private final ArchivingAdaptor archivingAdaptor;

    public void verifyUser(Long userId, Long archivingId) {
        archivingAdaptor.findById(archivingId).validateUser(userId);
    }

    public void validatePublicStatus(Long archivingId, Long userId) {
        archivingAdaptor.findById(archivingId).validatePublicStatus(userId);
    }

    public void validateDeleteStatus(Long archivingId, Long userId) {
        archivingAdaptor.findById(archivingId).validateDeleteStatus(userId);
    }

    public void validateExistArchiving(Long archivingId) {
        if (!archivingAdaptor.queryArchivingExist(archivingId)) {
            throw ArchivingNotFoundException.EXCEPTION;
        }
    }

    public void validateAlreadyPinStatus(Long archivingId, Long userId) {
        if (archivingAdaptor.findById(archivingId).getPinUserId().contains(userId)) {
            throw AlreadyPinnedArchivingException.EXCEPTION;
        }
    }

    public void validateNotPinStatus(Long archivingId, Long userId) {
        if (!archivingAdaptor.findById(archivingId).getPinUserId().contains(userId)) {
            throw NotPinnedArchivingException.EXCEPTION;
        }
    }

    public void validateArchivingUser(Long archivingId, Long userId) {
        archivingAdaptor.findById(archivingId).validateUser(userId);
    }
}
