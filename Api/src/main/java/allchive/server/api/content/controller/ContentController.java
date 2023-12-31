package allchive.server.api.content.controller;


import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.content.model.dto.request.UpdateContentRequest;
import allchive.server.api.content.model.dto.response.ContentTagInfoResponse;
import allchive.server.api.content.model.dto.response.ContentTagResponse;
import allchive.server.api.content.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "4. [content]")
public class ContentController {
    private final CreateContentUseCase createContentUseCase;
    private final GetContentUseCase getContentUseCase;
    private final DeleteContentUseCase deleteContentUseCase;
    private final UpdateContentUseCase updateContentUseCase;
    private final GetContentInfoUseCase getContentInfoUseCase;

    @Operation(summary = "컨텐츠를 생성합니다.")
    @PostMapping()
    public ContentTagResponse createContent(
            @RequestBody CreateContentRequest createContentRequest) {
        return createContentUseCase.execute(createContentRequest);
    }

    @Operation(summary = "컨텐츠 내용을 가져옵니다.")
    @GetMapping(value = "/{contentId}")
    public ContentTagResponse getContent(@PathVariable Long contentId) {
        return getContentUseCase.execute(contentId);
    }

    @Operation(summary = "컨텐츠를 삭제합니다.")
    @DeleteMapping(value = "/{contentId}")
    public void deleteContent(@PathVariable Long contentId) {
        deleteContentUseCase.execute(contentId);
    }

    @Operation(summary = "컨텐츠를 수정합니다.")
    @PatchMapping(value = "/{contentId}")
    public void updateContent(
            @PathVariable Long contentId, @RequestBody UpdateContentRequest request) {
        updateContentUseCase.execute(contentId, request);
    }

    @Operation(summary = "컨텐츠 정보 수정시 보여줄 정보를 가져옵니다.")
    @GetMapping(value = "/{contentId}/info")
    public ContentTagInfoResponse getContentInfo(@PathVariable Long contentId) {
        return getContentInfoUseCase.execute(contentId);
    }
}
