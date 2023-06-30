package allchive.server.api.auth.service;

import allchive.server.api.auth.model.dto.response.OauthLoginLinkResponse;
import allchive.server.api.auth.model.dto.response.OauthTokenResponse;
import allchive.server.api.auth.model.dto.response.TokenAndUserResponse;
import allchive.server.api.auth.service.helper.KakaoOauthHelper;
import allchive.server.api.auth.service.helper.TokenGenerateHelper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class OauthRegisterUseCase {

    private final KakaoOauthHelper kakaoOauthHelper;
//    private final UserDomainService userDomainService;
    private final TokenGenerateHelper tokenGenerateHelper;

//    public AvailableRegisterResponse checkAvailableRegister(String idToken) {
//        OauthInfo oauthInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
//        return new AvailableRegisterResponse(userDomainService.checkUserCanRegister(oauthInfo));
//    }
//
//    public TokenAndUserResponse registerUserByOCIDToken(
//            String idToken, RegisterRequest registerUserRequest) {
//
//        OauthInfo oauthInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
//        User user =
//                userDomainService.registerUser(
//                        registerUserRequest.toProfile(),
//                        oauthInfo,
//                        registerUserRequest.getMarketingAgree());
//
//        return tokenGenerateHelper.execute(user);
//    }
//
//    public OauthTokenResponse getCredentialFromKaKao(String code, String referer) {
//
//        return OauthTokenResponse.from(kakaoOauthHelper.getOauthToken(code, referer));
//    }
//
//    public OauthTokenResponse getCredentialFromKaKaoTest(String code) {
//
//        return OauthTokenResponse.from(kakaoOauthHelper.getOauthTokenTest(code));
//    }
}
