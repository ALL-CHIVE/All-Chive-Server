package allchive.server.domain.domains.user.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.exception.exceptions.AlreadyExistScrapException;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class ScrapValidator {
    private final ScrapAdaptor scrapAdaptor;

    public void validateExistScrap(User user, Long archivingId) {
        if (scrapAdaptor.findByUserAndArchivingId(user, archivingId).isPresent()) {
            throw AlreadyExistScrapException.EXCEPTION;
        }
    }
}
