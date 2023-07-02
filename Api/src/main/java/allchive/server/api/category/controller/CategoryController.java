package allchive.server.api.category.controller;

import allchive.server.api.category.service.CreateCategoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "3. [category]")
public class CategoryController {
    private final CreateCategoryUseCase createCategoryUseCase;
    @Operation(summary = "로그아웃을 합니다.")
    @PostMapping()
    public void createCategory() {
        createCategoryUseCase.execute();
    }
}
