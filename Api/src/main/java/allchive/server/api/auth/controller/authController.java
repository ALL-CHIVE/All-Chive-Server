package allchive.server.api.auth.controller;


import allchive.server.api.auth.service.LogOutUserUseCase;
import allchive.server.api.auth.service.WithdrawUserUseCase;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
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
public class authController {
    private final WithdrawUserUseCase withdrawUserUseCase;
    private final LogOutUserUseCase logOutUserUseCase;

    @Operation(summary = "회원탈퇴를 합니다.")
    @DeleteMapping("/withdrawal/{provider}")
    public void withDrawUser(@PathVariable OauthProvider provider) {
        withdrawUserUseCase.execute(provider);
    }

    @Operation(summary = "로그아웃을 합니다.")
    @PostMapping("/logout")
    public void logOutUser() {
        logOutUserUseCase.execute();
    }
}
