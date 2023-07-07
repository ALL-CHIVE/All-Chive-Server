package allchive.server.api.content.model.dto.response;

import allchive.server.core.annotation.DateFormat;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import allchive.server.tag.model.dto.response.TagResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ContentTagResponse {
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

    @Schema(
            type = "string",
            pattern = "yyyy.MM.dd",
            defaultValue = "2023.07.02",
            description = "컨텐츠 생성일자")
    @DateFormat
    private LocalDateTime contentCreatedAt;

    private List<TagResponse> tagList;

    @Builder
    private ContentTagResponse(Long contentId, String contentTitle, ContentType contentType,
                              String link, String imgUrl, LocalDateTime contentCreatedAt,
                              List<TagResponse> tagList) {
        this.contentId = contentId;
        this.contentTitle = contentTitle;
        this.contentType = contentType;
        this.link = link;
        this.imgUrl = imgUrl;
        this.contentCreatedAt = contentCreatedAt;
        this.tagList = tagList;
    }

    public static ContentTagResponse of(Content content, List<TagResponse> tagList) {
        return ContentTagResponse.builder()
                .contentId(content.getId())
                .contentTitle(content.getTitle())
                .contentType(content.getContentType())
                .link(content.getLinkUrl())
                .imgUrl(content.getImageUrl())
                .contentCreatedAt(content.getCreatedAt())
                .tagList(tagList)
                .build();
    }
}
