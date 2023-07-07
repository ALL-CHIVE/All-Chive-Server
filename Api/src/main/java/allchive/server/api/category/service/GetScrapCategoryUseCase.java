package allchive.server.api.category.service;


import allchive.server.api.category.model.dto.response.CategoryResponse;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.enums.Subject;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetScrapCategoryUseCase {
    private final ScrapAdaptor scrapAdaptor;
    private final CategoryAdaptor categoryAdaptor;

    @Transactional(readOnly = true)
    public SliceResponse<CategoryResponse> execute(Subject subject, Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Long> categoryIdList =
                scrapAdaptor.findAllByUserId(userId).stream().map(Scrap::getCategoryId).toList();
        Slice<CategoryResponse> categorySlices =
                categoryAdaptor
                        .querySliceCategoryIn(categoryIdList, subject, pageable)
                        .map(
                                category ->
                                        CategoryResponse.of(
                                                category,
                                                categoryIdList.contains(category.getId())));
        return SliceResponse.of(categorySlices);
    }
}
