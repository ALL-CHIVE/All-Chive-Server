package allchive.server.domain.domains.user.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.exception.exceptions.AlreadyExistScrapException;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class ScrapValidator {
    private final UserAdaptor userAdaptor;
    private final ScrapAdaptor scrapAdaptor;

    public void validateExistScrap(Long userId, Long archivingId) {
        User user = userAdaptor.findById(userId);
        if (isScrapExist(user, archivingId)) {
            throw AlreadyExistScrapException.EXCEPTION;
        }
    }

    private Boolean isScrapExist(User user, Long archivingId) {
        scrapAdaptor.findByUserAndArchivingId(user, archivingId);
        return Boolean.TRUE;
    }
}
