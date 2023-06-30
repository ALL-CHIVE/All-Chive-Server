package allchive.server.domain.domains.user.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@DomainService
@RequiredArgsConstructor
public class UserDomainService {
    private final UserAdaptor userAdaptor;

    public Boolean checkUserCanLogin(OauthInfo oauthInfo) {
        return userAdaptor.exist(oauthInfo);
    }

    //    @Transactional
    //    public User registerUser(Profile profile, OauthInfo oauthInfo, Boolean marketingAgree) {
    //        validUserCanRegister(oauthInfo);
    //        User newUser =
    //                User.builder()
    //                        .profile(profile)
    //                        .marketingAgree(marketingAgree)
    //                        .oauthInfo(oauthInfo)
    //                        .build();
    //        userRepository.save(newUser);
    //        return newUser;
    //    }

    @Transactional
    public User loginUser(OauthInfo oauthInfo) {
        User user = userAdaptor.queryUserByOauthInfo(oauthInfo);
        user.login();
        return user;
    }
}
