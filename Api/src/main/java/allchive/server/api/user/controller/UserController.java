package allchive.server.api.user.controller;

import allchive.server.api.category.model.dto.request.CreateCategoryRequest;
import allchive.server.api.user.model.dto.response.GetUserProfileResponse;
import allchive.server.api.user.service.GetUserProfileUseCase;
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

    @Operation(summary = "아카이빙 현황 + 내 프로필(사진, 이름) 가져오기")
    @GetMapping()
    public GetUserProfileResponse createCategory() {
        return getUserProfileUseCase.execute();
    }

}
