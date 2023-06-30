package allchive.server.api.auth.controller;


import allchive.server.api.auth.model.dto.response.OauthLoginLinkResponse;
import allchive.server.api.auth.model.dto.response.SignInResponse;
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
    private final OauthSigninUseCase oauthSigninUseCase;
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Operation(summary = "oauth 링크발급 (클라)", description = "oauth 링크를 받아볼수 있습니다.")
    @GetMapping("/link/{provider}")
    public OauthLoginLinkResponse getKakaoOauthLink(
            @PathVariable("provider") OauthProvider provider,
            @RequestHeader(value = "referer", required = false) String referer,
            @RequestHeader(value = "host", required = false) String host) {
        if (referer.contains(host)) {
            String format = String.format("https://%s/", host);
            return oauthLinkUseCase.getKaKaoOauthLink(provider, format);
        }
        return oauthLinkUseCase.getKaKaoOauthLink(provider, referer);
    }

    @Operation(summary = "oauth 링크발급 (server)", description = "oauth 링크를 받아볼수 있습니다.")
    @GetMapping("/link/{provider}/test")
    public OauthLoginLinkResponse getKakaoOauthLinkTest(
            @PathVariable("provider") OauthProvider provider) {
        return oauthLinkUseCase.getKaKaoOauthLinkTest(provider);
    }

    @Operation(summary = "로그인 / 회원가입, referer,host 입력 안하셔도 됩니다!")
    @GetMapping("/signin/{provider}")
    public SignInResponse getCredential(
            @PathVariable("provider") OauthProvider provider,
            @RequestParam("code") String code,
            @RequestHeader(value = "referer", required = false) String referer,
            @RequestHeader(value = "host", required = false) String host) {
        if (referer.contains(host)) {
            String format = String.format("https://%s/", host);
            return oauthSigninUseCase.execute(provider, code, format);
        }
        return oauthSigninUseCase.execute(provider, code, referer);
    }

    @Operation(summary = "로그인 / 회원가입, referer,host 입력 안하셔도 됩니다!")
    @GetMapping("/signin/{provider}/test")
    public SignInResponse getCredential(
            @PathVariable("provider") OauthProvider provider, @RequestParam("code") String code) {
        return oauthSigninUseCase.executeTest(provider, code);
    }
}
