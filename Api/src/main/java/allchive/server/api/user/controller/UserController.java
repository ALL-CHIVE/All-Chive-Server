package allchive.server.api.user.controller;

import allchive.server.api.user.model.dto.request.CheckUserNicknameRequest;
import allchive.server.api.user.model.dto.request.UpdateUserInfoRequest;
import allchive.server.api.user.model.dto.response.GetUserInfoResponse;
import allchive.server.api.user.model.dto.response.GetUserProfileResponse;
import allchive.server.api.user.service.CheckUserNicknameUseCase;
import allchive.server.api.user.service.GetUserInfoUseCase;
import allchive.server.api.user.service.GetUserProfileUseCase;
import allchive.server.api.user.service.UpdateUserInfoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "2. [user]")
public class UserController {
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final GetUserInfoUseCase getUserInfoUseCase;
    private final UpdateUserInfoUseCase updateUserInfoUseCase;
    private final CheckUserNicknameUseCase checkUserNicknameUseCase;

    @Operation(summary = "아카이빙 현황과 내 프로필을 가져옵니다.")
    @GetMapping()
    public GetUserProfileResponse getUserProfile() {
        return getUserProfileUseCase.execute();
    }

    @Operation(summary = "내 정보를 가져옵니다.")
    @GetMapping(value = "/info")
    public GetUserInfoResponse getUserInfo() {
        return getUserInfoUseCase.execute();
    }

    @Operation(summary = "내 정보를 수정합니다.")
    @PostMapping(value = "/info")
    public void getUserInfo(@RequestBody UpdateUserInfoRequest updateUserInfoRequest) {
        updateUserInfoUseCase.execute(updateUserInfoRequest);
    }

    @Operation(summary = "닉네임 중복체크합니다.")
    @PostMapping(value = "/nickname")
    public void checkUserNickname(@RequestBody CheckUserNicknameRequest request) {
        checkUserNicknameUseCase.execute(request);
    }
}
