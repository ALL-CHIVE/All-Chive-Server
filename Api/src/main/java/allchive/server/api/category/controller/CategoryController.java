package allchive.server.api.category.controller;


import allchive.server.api.category.model.dto.request.CreateCategoryRequest;
import allchive.server.api.category.model.dto.request.UpdateCategoryRequest;
import allchive.server.api.category.model.dto.response.CategoryContentsResponse;
import allchive.server.api.category.model.dto.response.CategoryResponse;
import allchive.server.api.category.model.dto.response.CategoryTitleResponse;
import allchive.server.api.category.service.*;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.domain.domains.category.domain.enums.Topic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "3. [category]")
public class CategoryController {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final GetArchivedCategoryUseCase getArchivedCategoryUseCase;
    private final GetScrapCategoryUseCase getScrapCategoryUseCase;
    private final GetCategoryTitleUseCase getCategoryTitleUseCase;
    private final GetCategoryContentsUseCase getCategoryContentsUseCase;
    private final UpdateCategoryScrapUseCase updateCategoryScrapUseCase;
    private final UpdateCategoryPinUseCase updateCategoryPinUseCase;

    @Operation(summary = "카테고리를 생성합니다.")
    @PostMapping()
    public void createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        createCategoryUseCase.execute(createCategoryRequest);
    }

    @Operation(summary = "카테고리를 수정합니다.")
    @PatchMapping(value = "/{categoryId}")
    public void updateCategory(
            @PathVariable("categoryId") Long categoryId,
            @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        updateCategoryUseCase.execute(categoryId, updateCategoryRequest);
    }

    @Operation(summary = "카테고리를 수정합니다.")
    @DeleteMapping(value = "/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        deleteCategoryUseCase.execute(categoryId);
    }

    @Operation(
            summary = "주제별 카테고리 리스트를 가져옵니다.",
            description = "sort parameter는 입력하지 말아주세요! sorting : 스크랩 여부 -> 스크랩 수 -> 생성일자")
    @GetMapping()
    public SliceResponse<CategoryResponse> getCategory(
            @RequestParam("topic") Topic topic,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return getCategoryUseCase.execute(topic, pageable);
    }

    @Operation(
            summary = "내 아카이빙 주제별 카테고리 리스트를 가져옵니다.",
            description = "sort parameter는 입력하지 말아주세요! sorting : 고정 -> 스크랩 수 -> 생성일자")
    @GetMapping(value = "/me/archiving")
    public SliceResponse<CategoryResponse> getArchivedCategory(
            @RequestParam("topic") Topic topic,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return getArchivedCategoryUseCase.execute(topic, pageable);
    }

    @Operation(
            summary = "스크랩 주제별 카테고리 리스트를 가져옵니다.",
            description = "sort parameter는 입력하지 말아주세요! sorting : 스크랩 수 -> 생성일자")
    @GetMapping(value = "/me/scrap")
    public SliceResponse<CategoryResponse> getScrapCategory(
            @RequestParam("topic") Topic topic,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return getScrapCategoryUseCase.execute(topic, pageable);
    }

    @Operation(summary = "사용 중인 주제 & 카테고리 리스트를 가져옵니다. (컨텐츠 추가 시 사용)")
    @GetMapping(value = "/lists")
    public CategoryTitleResponse getScrapCategory() {
        return getCategoryTitleUseCase.execute();
    }

    @Operation(summary = "카테고리별 컨텐츠 리스트를 가져옵니다.")
    @GetMapping(value = "/{categoryId}/contents")
    public CategoryContentsResponse getCategoryContents(
            @PathVariable("categoryId") Long categoryId,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return getCategoryContentsUseCase.execute(categoryId, pageable);
    }

    @Operation(summary = "카테고리를 스크랩합니다.", description = "스크랩 취소면 cancel에 true 값 보내주세요")
    @PatchMapping(value = "/{categoryId}/scrap")
    public void updateCategoryScrap(
            @RequestParam("cancel") Boolean cancel, @PathVariable("categoryId") Long categoryId) {
        updateCategoryScrapUseCase.execute(categoryId, cancel);
    }

    @Operation(summary = "카테고리를 고정합니다.", description = "고정 취소면 cancel에 true 값 보내주세요")
    @PatchMapping(value = "/{categoryId}/pin")
    public void updateCategoryPin(
            @RequestParam("cancel") Boolean cancel, @PathVariable("categoryId") Long categoryId) {
        updateCategoryPinUseCase.execute(categoryId, cancel);
    }
}
