package allchive.server.api.category.service;


import allchive.server.api.category.model.dto.response.CategoryResponse;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.block.adaptor.BlockAdaptor;
import allchive.server.domain.domains.block.domain.Block;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.enums.Topic;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetCategoryUseCase {
    private final ScrapAdaptor scrapAdaptor;
    private final BlockAdaptor blockAdaptor;
    private final CategoryAdaptor categoryAdaptor;

    @Transactional(readOnly = true)
    public SliceResponse<CategoryResponse> execute(Topic topic, Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Long> categoryIdList =
                scrapAdaptor.findAllByUserId(userId).stream().map(Scrap::getCategoryId).toList();
        List<Long> blockList =
                blockAdaptor.findByBlockFrom(userId).stream().map(Block::getBlockUser).toList();
        Slice<CategoryResponse> categorySlices =
                categoryAdaptor
                        .querySliceCategoryExceptBlock(categoryIdList, blockList, topic, pageable)
                        .map(
                                category ->
                                        CategoryResponse.of(
                                                category,
                                                categoryIdList.contains(category.getId())));
        return SliceResponse.of(categorySlices);
    }
}
