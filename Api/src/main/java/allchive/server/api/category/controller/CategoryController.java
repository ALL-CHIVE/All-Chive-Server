package allchive.server.api.category.controller;

import allchive.server.api.category.model.dto.request.CreateCategoryRequest;
import allchive.server.api.category.model.dto.request.UpdateCategoryRequest;
import allchive.server.api.category.service.CreateCategoryUseCase;
import allchive.server.api.category.service.UpdateCategoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "3. [category]")
public class CategoryController {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    @Operation(summary = "카테고리를 생성합니다.")
    @PostMapping()
    public void createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        createCategoryUseCase.execute(createCategoryRequest);
    }

    @Operation(summary = "카테고리를 수정합니다.")
    @PatchMapping(value = "/{categoryId}")
    public void updateCategory(@RequestParam("categoryId") Long categoryId,
            @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        updateCategoryUseCase.execute(categoryId, updateCategoryRequest);
    }
}
