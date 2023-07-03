package allchive.server.api.category.service;


import allchive.server.api.category.model.dto.response.CategoryResponse;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.block.adaptor.BlockAdaptor;
import allchive.server.domain.domains.block.domain.Block;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.adaptor.CategoryPinAdaptor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetCategoryUseCase {
    private final BlockAdaptor blockAdaptor;
    private final CategoryAdaptor categoryAdaptor;
    private final CategoryPinAdaptor categoryPinAdaptor;

    @Transactional(readOnly = true)
    public SliceResponse<CategoryResponse> execute(Pageable pageable) {
        // 유저 블락 id -> 카테고리 쿼리
        Long userId = SecurityUtil.getCurrentUserId();
        List<Long> blockList =
                blockAdaptor.findByBlockFrom(userId).stream().map(Block::getBlockUser).toList();
        Slice<CategoryResponse> categorySlices =
                categoryAdaptor
                        .querySliceCategoryExceptBlock(blockList, userId, pageable)
                        .map(
                                category ->
                                        CategoryResponse.of(
                                                category,
                                                category.getPinUserId().contains(userId)));
        //                .map(category -> CategoryResponse.of(category, false));

        return SliceResponse.of(categorySlices);
    }
}
