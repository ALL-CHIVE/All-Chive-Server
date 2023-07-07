package allchive.server.api.category.model.dto.response;


import allchive.server.api.common.util.UrlUtil;
import allchive.server.core.annotation.DateFormat;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.domain.enums.Topic;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryResponse {
    @Schema(description = "카테고리 고유 아이디")
    private Long categoryId;

    @Schema(description = "카테고리 제목")
    private String title;

    @Schema(defaultValue = "카테고리 이미지 url", description = "카테고리 이미지 url")
    private String imageUrl;

    @Schema(defaultValue = "2023.07.02", description = "카테고리 생성일자")
    @DateFormat
    private LocalDateTime createdAt;

    @Schema(defaultValue = "카테고리 주제", description = "카테고리 주제")
    private Topic topic;

    @Schema(description = "카테고리 컨텐츠 중 이미지 수")
    private Long imgCnt;

    @Schema(description = "카테고리 컨텐츠 중 링크 수")
    private Long linkCnt;

    @Schema(description = "카테고리 스크랩 수")
    private Long scrapCnt;

    @Schema(description = "카테고리 스크랩/고정 여부, true == 스크랩/고정됨")
    private boolean markStatus;

    @Builder
    private CategoryResponse(
            Long categoryId,
            String title,
            String imageUrl,
            LocalDateTime createdAt,
            Topic topic,
            Long imgCnt,
            Long linkCnt,
            Long scrapCnt,
            boolean markStatus) {
        this.categoryId = categoryId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.topic = topic;
        this.imgCnt = imgCnt;
        this.linkCnt = linkCnt;
        this.scrapCnt = scrapCnt;
        this.markStatus = markStatus;
    }

    public static CategoryResponse of(Category category, boolean markStatus) {
        return CategoryResponse.builder()
                .categoryId(category.getId())
                .imageUrl(UrlUtil.toAssetUrl(category.getImageUrl()))
                .title(category.getTitle())
                .createdAt(category.getCreatedAt())
                .topic(category.getTopic())
                .imgCnt(category.getImgCnt())
                .linkCnt(category.getLinkCnt())
                .scrapCnt(category.getScrapCnt())
                .markStatus(markStatus)
                .build();
    }
}
