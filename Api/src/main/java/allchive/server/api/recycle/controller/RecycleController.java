package allchive.server.api.recycle.controller;

import allchive.server.api.recycle.model.dto.request.RestoreObjectRequest;
import allchive.server.api.recycle.service.RestoreObjectUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recycles")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "6. [recycle]")
public class RecycleController {
    private final RestoreObjectUseCase restoreObjectUseCase;

    @Operation(summary = "아카이빙을 생성합니다.")
    @PatchMapping()
    public void restoreObject(@RequestBody RestoreObjectRequest request) {
        restoreObjectUseCase.execute(request);
    }
}
