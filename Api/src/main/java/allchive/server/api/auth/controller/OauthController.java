package allchive.server.api.auth.controller;


import allchive.server.api.auth.model.dto.response.OauthLoginLinkResponse;
import allchive.server.api.auth.model.dto.response.OauthTokenResponse;
import allchive.server.api.auth.service.*;
import allchive.server.core.helper.SpringEnvironmentHelper;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/oauth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "1-2. [oauth]")
public class OauthController {
    private final OauthLinkUseCase oauthLinkUseCase;
    private final OauthRegisterUseCase oauthRegisterUseCase;
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Operation(summary = "kakao oauth 링크발급 (server)", description = "kakao 링크를 받아볼수 있습니다.")
    @GetMapping("/oauth/link/{provider}/test")
    public OauthLoginLinkResponse getKakaoOauthLinkTest(@PathVariable("provider") OauthProvider provider) {
        return oauthLinkUseCase.getKaKaoOauthLinkTest(provider);
    }

    @Operation(summary = "kakao oauth 링크발급 (클라)", description = "kakao 링크를 받아볼수 있습니다.")
    @GetMapping("/oauth/link/{provider}")
    public OauthLoginLinkResponse getKakaoOauthLink(
            @PathVariable("provider") OauthProvider provider,
            @RequestHeader(value = "referer", required = false) String referer,
            @RequestHeader(value = "host", required = false) String host) {
        return oauthLinkUseCase.getKaKaoOauthLink(referer, provider);
    }

//    @Operation(summary = "카카오 code 요청받는 곳입니다. referer,host 입력 안하셔도 됩니다!")
//    @GetMapping("/oauth/code/{provider}")
//    public OauthTokenResponse getCredentialFromKaKao(
//            @PathVariable("provider") OauthProvider provider,
//            @RequestParam("code") String code,
//            @RequestHeader(value = "referer", required = false) String referer,
//            @RequestHeader(value = "host", required = false) String host) {
//        // 스테이징, prod 서버에 배포된 클라이언트에 해당
//        if (referer.contains(host)) {
//            log.info("/oauth/kakao" + host);
//            String format = String.format("https://%s/", host);
//            if (referer.contains("admin")) {
//                return oauthRegisterUseCase.getCredentialFromKaKao(code, format + "admin");
//            }
//            return oauthRegisterUseCase.getCredentialFromKaKao(code, format);
//        } else if (referer.contains("5173")) {
//            return oauthRegisterUseCase.getCredentialFromKaKao(code, referer + "admin");
//        }
//        return oauthRegisterUseCase.getCredentialFromKaKao(code, referer);
//
//        // 프론트 개발자가 로컬에서 개발 테스트 할 때 해당 https://localhost:3000/
//    }

//    @Operation(summary = "회원가입이 가능한지 id token 으로 확인합니다.")
//    @GetMapping("/oauth/kakao/register/valid")
//    public AvailableRegisterResponse kakaoAuthCheckRegisterValid(
//            @RequestParam("id_token") String token) {
//        return oauthRegisterUseCase.checkAvailableRegister(token);
//    }

//    @Operation(summary = "id_token 으로 회원가입을 합니다.")
//    @PostMapping("/oauth/signin/{provider}")
//    public ResponseEntity<TokenAndUserResponse> kakaoAuthCheckRegisterValid(
//            @PathVariable("provider") OauthProvider provider,
//            @RequestParam("id_token") String token,
//            @Valid @RequestBody RegisterRequest registerRequest) {
//        TokenAndUserResponse tokenAndUserResponse =
//                oauthRegisterUseCase.registerUserByOCIDToken(token, registerRequest);
//        return ResponseEntity.ok()
//                .headers(cookieHelper.getTokenCookies(tokenAndUserResponse))
//                .body(tokenAndUserResponse);
//    }

}
