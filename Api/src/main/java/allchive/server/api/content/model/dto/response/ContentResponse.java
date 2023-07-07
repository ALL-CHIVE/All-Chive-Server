package allchive.server.api.content.model.dto.response;


import allchive.server.core.annotation.DateFormat;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ContentResponse {
    @Schema(description = "컨텐츠 고유번호")
    private Long contentId;

    @Schema(description = "컨텐츠 제목")
    private String contentTitle;

    @Schema(description = "컨텐츠 종류")
    private ContentType contentType;

    @Schema(defaultValue = "컨텐츠 링크", description = "컨텐츠 링크")
    private String link;

    @Schema(defaultValue = "컨텐츠 이미지 url", description = "컨텐츠 이미지 url")
    private String imgUrl;

    @Schema(defaultValue = "2023.07.02", description = "컨텐츠 생성일자")
    @DateFormat
    private LocalDateTime contentCreatedAt;

    @Schema(description = "컨텐츠 태그")
    private String Tag;

    @Schema(description = "컨텐츠 태그 총 개수")
    private Long TagCount;

    @Builder
    private ContentResponse(
            Long contentId,
            String contentTitle,
            ContentType contentType,
            LocalDateTime contentCreatedAt,
            String tag,
            String link,
            String imgUrl,
            Long tagCount) {
        this.contentId = contentId;
        this.contentTitle = contentTitle;
        this.contentType = contentType;
        this.contentCreatedAt = contentCreatedAt;
        this.link = link;
        this.imgUrl = imgUrl;
        Tag = tag;
        TagCount = tagCount;
    }

    public static ContentResponse of(Content content, String tag, Long tagCount) {
        return ContentResponse.builder()
                .contentId(content.getId())
                .contentTitle(content.getTitle())
                .contentType(content.getContentType())
                .link(content.getLinkUrl())
                .imgUrl(content.getImageUrl())
                .contentCreatedAt(content.getCreatedAt())
                .tag(tag)
                .tagCount(tagCount)
                .build();
    }
}
