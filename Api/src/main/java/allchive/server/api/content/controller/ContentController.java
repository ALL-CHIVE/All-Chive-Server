package allchive.server.api.content.controller;


import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.content.model.dto.request.UpdateContentRequest;
import allchive.server.api.content.model.dto.response.ContentTagResponse;
import allchive.server.api.content.service.CreateContentUseCase;
import allchive.server.api.content.service.DeleteContentUseCase;
import allchive.server.api.content.service.GetContentUseCase;
import allchive.server.api.content.service.UpdateContentUseCase;
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

    @Operation(summary = "컨텐츠를 생성합니다.")
    @PostMapping()
    public void createContent(@RequestBody CreateContentRequest createContentRequest) {
        createContentUseCase.execute(createContentRequest);
    }

    @Operation(summary = "컨텐츠 내용을 가져옵니다.")
    @GetMapping(value = "/{contentId}")
    public ContentTagResponse createContent(@PathVariable Long contentId) {
        return getContentUseCase.execute(contentId);
    }

    @Operation(summary = "컨텐츠를 삭제합니다.")
    @DeleteMapping(value = "/{contentId}")
    public void deleteContent(@PathVariable Long contentId) {
        deleteContentUseCase.execute(contentId);
    }

    @Operation(summary = "컨텐츠를 수정합니다.")
    @PatchMapping(value = "/{contentId}")
    public void updateContent(@PathVariable Long contentId,
                              @RequestBody UpdateContentRequest request) {
        updateContentUseCase.execute(contentId, request);
    }
}
