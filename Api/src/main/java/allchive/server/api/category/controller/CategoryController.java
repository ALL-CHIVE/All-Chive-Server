package allchive.server.api.category.controller;


import allchive.server.api.category.model.dto.request.CreateCategoryRequest;
import allchive.server.api.category.model.dto.request.UpdateCategoryRequest;
import allchive.server.api.category.model.dto.response.CategoryResponse;
import allchive.server.api.category.service.CreateCategoryUseCase;
import allchive.server.api.category.service.DeleteCategoryUseCase;
import allchive.server.api.category.service.GetCategoryUseCase;
import allchive.server.api.category.service.UpdateCategoryUseCase;
import allchive.server.api.common.slice.SliceResponse;
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

    @Operation(summary = "카테고리를 생성합니다.")
    @PostMapping()
    public void createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        createCategoryUseCase.execute(createCategoryRequest);
    }

    @Operation(summary = "카테고리를 수정합니다.")
    @PatchMapping(value = "/{categoryId}")
    public void updateCategory(
            @RequestParam("categoryId") Long categoryId,
            @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        updateCategoryUseCase.execute(categoryId, updateCategoryRequest);
    }

    @Operation(summary = "카테고리를 수정합니다.")
    @DeleteMapping(value = "/{categoryId}")
    public void deleteCategory(@RequestParam("categoryId") Long categoryId) {
        deleteCategoryUseCase.execute(categoryId);
    }

    @Operation(summary = "카테고리 리스트를 스크랩 많은 순서로 가져옵니다.", description = "sort parameter는 입력하지 말아주세요!")
    @GetMapping()
    public SliceResponse<CategoryResponse> deleteCategory(
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return getCategoryUseCase.execute(pageable);
    }
}
