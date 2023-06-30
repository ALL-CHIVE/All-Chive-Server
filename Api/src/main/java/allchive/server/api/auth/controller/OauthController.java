package allchive.server.api.auth.controller;


import allchive.server.api.auth.model.dto.request.RegisterRequest;
import allchive.server.api.auth.model.dto.response.OauthLoginLinkResponse;
import allchive.server.api.auth.model.dto.response.OauthRegisterResponse;
import allchive.server.api.auth.model.dto.response.OauthSignInResponse;
import allchive.server.api.auth.service.*;
import allchive.server.core.helper.SpringEnvironmentHelper;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/oauth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "1-2. [oauth]")
public class OauthController {
    private final OauthLinkUseCase oauthLinkUseCase;
    private final OauthLoginUseCase oauthLoginUseCase;
    private final OauthRegisterUseCase oauthRegisterUseCase;
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Operation(summary = "oauth 링크발급", description = "oauth 링크를 받아볼수 있습니다.")
    @GetMapping("/link/{provider}")
    public OauthLoginLinkResponse getOauthLink(
            @PathVariable("provider") OauthProvider provider,
            @RequestHeader(value = "referer", required = false) String referer,
            @RequestHeader(value = "host", required = false) String host) {
        if (referer.contains(host)) {
            String format = String.format("https://%s/", host);
            return oauthLinkUseCase.getKaKaoOauthLink(provider, format);
        }
        return oauthLinkUseCase.getKaKaoOauthLink(provider, referer);
    }

    @Operation(summary = "개발용 oauth 링크발급", deprecated = true)
    @GetMapping("/link/{provider}/test")
    public OauthLoginLinkResponse getOauthLinkTest(
            @PathVariable("provider") OauthProvider provider) {
        if (springEnvironmentHelper.isProdProfile()) {}

        return oauthLinkUseCase.getKaKaoOauthLinkTest(provider);
    }

    @Operation(
            summary = "로그인",
            description = "referer, host 입력 안하셔도 됩니다!" + "회원가입 안된 유저일 경우, canLogin=false 값을 보냅니다!")
    @PostMapping("/login/{provider}")
    public OauthSignInResponse oauthUserLogin(
            @PathVariable("provider") OauthProvider provider,
            @RequestParam("code") String code,
            @RequestHeader(value = "referer", required = false) String referer,
            @RequestHeader(value = "host", required = false) String host) {
        if (referer.contains(host)) {
            String format = String.format("https://%s/", host);
            return oauthLoginUseCase.execute(provider, code, format);
        }
        return oauthLoginUseCase.execute(provider, code, referer);
    }

    @Operation(summary = "개발용 로그인", deprecated = true)
    @PostMapping("/login/{provider}/test")
    public OauthSignInResponse oauthUserLoginTest(
            @PathVariable("provider") OauthProvider provider, @RequestParam("code") String code) {
        return oauthLoginUseCase.executeTest(provider, code);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/register/{provider}/test")
    public OauthRegisterResponse oauthUserRegister(
            @PathVariable("provider") OauthProvider provider,
            @RequestParam("idToken") String idToken,
            @RequestBody RegisterRequest registerRequest) {
        return oauthRegisterUseCase.execute(provider, idToken, registerRequest);
    }
}
