package allchive.server.domain.domains.user.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.exception.exceptions.DuplicatedNicknameException;
import allchive.server.domain.domains.user.validator.UserValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
public class UserDomainService {
    private final UserAdaptor userAdaptor;
    private final UserValidator userValidator;

    public Boolean checkUserCanLogin(OauthInfo oauthInfo) {
        return userAdaptor.exist(oauthInfo);
    }

    @Transactional
    public User registerUser(
            String name,
            String email,
            String nickname,
            String profileImgUrl,
            List<Category> categoryList,
            boolean marketingAgreement,
            OauthInfo oauthInfo) {
        userValidator.validUserCanRegister(oauthInfo);
        final User newUser =
                User.of(
                        name,
                        email,
                        nickname,
                        profileImgUrl,
                        categoryList,
                        marketingAgreement,
                        oauthInfo);
        userAdaptor.save(newUser);
        return newUser;
    }

    @Transactional
    public User loginUser(OauthInfo oauthInfo) {
        User user = userAdaptor.findByOauthInfo(oauthInfo);
        user.login();
        return user;
    }

    @Transactional
    public void deleteUserById(Long userId) {
        User user = userAdaptor.findById(userId);
        user.withdrawUser();
    }

    public void updateUserInfo(
            Long userId, String name, String email, String nickname, String imgUrl) {
        User user = userAdaptor.findById(userId);
        user.updateInfo(name, email, nickname, imgUrl);
    }

    public void checkUserNickname(String nickname) {
        if (userAdaptor.existsByNickname(nickname)) {
            throw DuplicatedNicknameException.EXCEPTION;
        }
    }
}
