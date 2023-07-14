package allchive.server.api.recycle.controller;

import allchive.server.api.recycle.model.dto.request.RestoreDeletedObjectRequest;
import allchive.server.api.recycle.model.dto.response.DeletedObjectResponse;
import allchive.server.api.recycle.service.GetDeletedObjectUseCase;
import allchive.server.api.recycle.service.RestoreDeletedObjectUseCase;
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
    private final RestoreDeletedObjectUseCase restoreDeletedObjectUseCase;
    private final GetDeletedObjectUseCase getDeletedObjectUseCase;

    @Operation(summary = "삭제된 아카이빙, 컨텐츠를 복구합니다.")
    @PatchMapping()
    public void restoreDeletedObject(@RequestBody RestoreDeletedObjectRequest request) {
        restoreDeletedObjectUseCase.execute(request);
    }

    @Operation(summary = "삭제된 아카이빙, 컨텐츠를 가져옵니다.")
    @GetMapping()
    public DeletedObjectResponse getDeletedObject() {
        return getDeletedObjectUseCase.execute();
    }

}
