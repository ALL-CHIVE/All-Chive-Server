package allchive.server.domain.domains.user.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.exception.exceptions.AlreadySignUpUserException;
import allchive.server.domain.domains.user.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class UserValidator {
    private final UserAdaptor userAdaptor;

    public void validUserCanRegister(OauthInfo oauthInfo) {
        if (!checkUserCanRegister(oauthInfo)) throw AlreadySignUpUserException.EXCEPTION;
    }

    public Boolean checkUserCanRegister(OauthInfo oauthInfo) {
        return !userAdaptor.exist(oauthInfo);
    }

    public void validateUserStatusNormal(Long userId) {
        userAdaptor.findById(userId).validateUserStateNormal();
    }

    public void validateExist(Long userId) {
        if (!userAdaptor.existsById(userId)) {
            throw UserNotFoundException.EXCEPTION;
        }
    }
}
