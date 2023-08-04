package allchive.server.api.content.model.dto.response;


import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.tag.model.dto.response.TagResponse;
import allchive.server.core.annotation.DateFormat;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ContentTagResponse {
    @Schema(description = "컨텐츠 고유번호")
    private Long contentId;

    @Schema(description = "컨텐츠 제목")
    private String contentTitle;

    @Schema(description = "컨텐츠 종류")
    private ContentType contentType;

    @Schema(description = "메모")
    private String contentMemo;

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

    @Schema(description = "컨텐츠 소유자 고유번호")
    private Long ownerId;

    @Schema(description = "유저 소유 여부")
    private Boolean isMine;

    @Builder
    private ContentTagResponse(
            Long contentId,
            String contentTitle,
            ContentType contentType,
            String contentMemo,
            String link,
            String imgUrl,
            LocalDateTime contentCreatedAt,
            List<TagResponse> tagList,
            Long ownerId,
            Boolean isMine) {
        this.contentId = contentId;
        this.contentTitle = contentTitle;
        this.contentType = contentType;
        this.contentMemo = contentMemo;
        this.link = link;
        this.imgUrl = imgUrl;
        this.contentCreatedAt = contentCreatedAt;
        this.tagList = tagList;
        this.ownerId = ownerId;
        this.isMine = isMine;
    }

    public static ContentTagResponse of(
            Content content, List<TagResponse> tagList, Boolean isMine, Long ownerId) {
        return ContentTagResponse.builder()
                .contentId(content.getId())
                .contentTitle(content.getTitle())
                .contentType(content.getContentType())
                .contentMemo(content.getMemo())
                .link(content.getLinkUrl())
                .imgUrl(UrlUtil.toAssetUrl(content.getImageUrl()))
                .contentCreatedAt(content.getCreatedAt())
                .tagList(tagList)
                .ownerId(ownerId)
                .isMine(isMine)
                .build();
    }
}
