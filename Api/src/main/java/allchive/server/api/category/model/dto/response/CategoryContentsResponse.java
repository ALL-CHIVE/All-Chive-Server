package allchive.server.api.category.model.dto.response;


import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.content.model.dto.response.ContentResponse;
import allchive.server.domain.domains.category.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryContentsResponse {
    private SliceResponse<ContentResponse> contents;

    @Schema(description = "카테고리 제목")
    private String categoryTitle;

    @Schema(description = "카테고리 고유번호")
    private Long categoryId;

    @Schema(description = "카테고리의 총 컨텐츠 개수")
    private Long totalContentsCount;

    @Builder
    private CategoryContentsResponse(
            SliceResponse<ContentResponse> contents,
            String categoryTitle,
            Long categoryId,
            Long totalContentsCount) {
        this.contents = contents;
        this.categoryTitle = categoryTitle;
        this.categoryId = categoryId;
        this.totalContentsCount = totalContentsCount;
    }

    public static CategoryContentsResponse of(
            SliceResponse<ContentResponse> contentResponseSlice, Category category) {
        return CategoryContentsResponse.builder()
                .categoryId(category.getId())
                .categoryTitle(category.getTitle())
                .totalContentsCount(category.getScrapCnt() + category.getImgCnt())
                .contents(contentResponseSlice)
                .build();
    }
}
