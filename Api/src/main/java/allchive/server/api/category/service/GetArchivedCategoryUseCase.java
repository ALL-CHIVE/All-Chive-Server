package allchive.server.api.category.service;


import allchive.server.api.category.model.dto.response.CategoryResponse;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetArchivedCategoryUseCase {
    private final CategoryAdaptor categoryAdaptor;

    @Transactional(readOnly = true)
    public SliceResponse<CategoryResponse> execute(Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        Slice<CategoryResponse> categorySlices =
                categoryAdaptor
                        .querySliceCategoryByUserId(userId, pageable)
                        .map(
                                category ->
                                        CategoryResponse.of(
                                                category,
                                                category.getPinUserId().contains(userId)));
        return SliceResponse.of(categorySlices);
    }
}
