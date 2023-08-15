package allchive.server.api.auth.controller;


import allchive.server.api.auth.model.dto.response.OauthRegisterResponse;
import allchive.server.api.auth.service.LogOutUserUseCase;
import allchive.server.api.auth.service.TokenRefreshUseCase;
import allchive.server.api.auth.service.WithdrawUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "1-1. [auth]")
public class AuthController {
    private final WithdrawUserUseCase withdrawUserUseCase;
    private final LogOutUserUseCase logOutUserUseCase;
    private final TokenRefreshUseCase tokenRefreshUseCase;

    @Operation(summary = "회원탈퇴를 합니다.")
    @DeleteMapping("/withdrawal")
    public void withDrawUser(
            @RequestParam(required = false, name = "appleCode", value = "") String appleCode,
            @RequestHeader(value = "referer", required = false) String referer) {
        withdrawUserUseCase.execute(appleCode, referer);
    }

    @Operation(summary = "로그아웃을 합니다.")
    @PostMapping("/logout")
    public void logOutUser() {
        logOutUserUseCase.execute();
    }

    @Operation(summary = "토큰 재발급을 합니다.")
    @PostMapping("/token/refresh")
    public OauthRegisterResponse refreshToken(
            @RequestParam(value = "refreshToken") String refreshToken) {
        return tokenRefreshUseCase.execute(refreshToken);
    }
}
