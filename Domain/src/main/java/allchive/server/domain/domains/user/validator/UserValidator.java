package allchive.server.domain.domains.user.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.UserState;
import allchive.server.domain.domains.user.exception.exceptions.AlreadySignUpUserException;
import allchive.server.domain.domains.user.exception.exceptions.ForbiddenUserException;
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
        if (!userAdaptor.findUserById(userId).getUserState().equals(UserState.NORMAL)) {
            throw ForbiddenUserException.EXCEPTION;
        }
    }
}
