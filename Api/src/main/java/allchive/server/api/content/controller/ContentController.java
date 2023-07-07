package allchive.server.api.content.controller;

import allchive.server.api.category.model.dto.request.CreateCategoryRequest;
import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.content.model.dto.response.ContentTagResponse;
import allchive.server.api.content.service.CreateContentUseCase;
import allchive.server.api.content.service.DeleteContentUseCase;
import allchive.server.api.content.service.GetContentUseCase;
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

    @Operation(summary = "컨텐츠 내용을 가져옵니다.")
    @DeleteMapping(value = "/{contentId}")
    public void deleteContent(@PathVariable Long contentId) {
        deleteContentUseCase.execute(contentId);
    }
}
