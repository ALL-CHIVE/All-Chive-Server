package allchive.server.api.category.service;


import allchive.server.api.category.model.dto.response.CategoryContentsResponse;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.response.ContentResponse;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.validator.CategoryValidator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.adaptor.ContentTagGroupAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetCategoryContentsUseCase {
    private final CategoryAdaptor categoryAdaptor;
    private final CategoryValidator categoryValidator;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupAdaptor contentTagGroupAdaptor;
    private final ContentMapper contentMapper;

    @Transactional(readOnly = true)
    public CategoryContentsResponse execute(Long categoryId, Pageable pageable) {
        Long userId = SecurityUtil.getCurrentUserId();
        categoryValidator.validationPublicStatus(categoryId, userId);
        Category category = categoryAdaptor.findById(categoryId);
        Slice<Content> contentList =
                contentAdaptor.querySliceContentByCategoryId(categoryId, pageable);
        List<ContentTagGroup> contentTagGroupList =
                contentTagGroupAdaptor.queryContentIn(contentList.getContent());
        Slice<ContentResponse> contentResponseSlice =
                contentList.map(
                        content -> contentMapper.toContentResponse(content, contentTagGroupList));
        return CategoryContentsResponse.of(SliceResponse.of(contentResponseSlice), category);
    }
}
