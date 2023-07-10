package allchive.server.api.archiving.model.dto.response;


import allchive.server.api.common.util.UrlUtil;
import allchive.server.core.annotation.DateFormat;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArchivingResponse {
    @Schema(description = "아카이빙 고유 아이디")
    private Long archivingId;

    @Schema(description = "아카이빙 제목")
    private String title;

    @Schema(defaultValue = "아카이빙 이미지 url", description = "아카이빙 이미지 url")
    private String imageUrl;

    @Schema(
            type = "string",
            pattern = "yyyy.MM.dd",
            defaultValue = "2023.07.02",
            description = "아카이빙 생성일자")
    @DateFormat
    private LocalDateTime createdAt;

    @Schema(defaultValue = "아카이빙 주제", description = "아카이빙 주제")
    private Category category;

    @Schema(description = "아카이빙 컨텐츠 중 이미지 수")
    private Long imgCnt;

    @Schema(description = "아카이빙 컨텐츠 중 링크 수")
    private Long linkCnt;

    @Schema(description = "아카이빙 스크랩 수")
    private Long scrapCnt;

    @Schema(description = "아카이빙 스크랩/고정 여부, true == 스크랩/고정됨")
    private boolean markStatus;

    @Builder
    private ArchivingResponse(
            Long archivingId,
            String title,
            String imageUrl,
            LocalDateTime createdAt,
            Category category,
            Long imgCnt,
            Long linkCnt,
            Long scrapCnt,
            boolean markStatus) {
        this.archivingId = archivingId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.category = category;
        this.imgCnt = imgCnt;
        this.linkCnt = linkCnt;
        this.scrapCnt = scrapCnt;
        this.markStatus = markStatus;
    }

    public static ArchivingResponse of(Archiving archiving, boolean markStatus) {
        return ArchivingResponse.builder()
                .archivingId(archiving.getId())
                .imageUrl(UrlUtil.toAssetUrl(archiving.getImageUrl()))
                .title(archiving.getTitle())
                .createdAt(archiving.getCreatedAt())
                .category(archiving.getCategory())
                .imgCnt(archiving.getImgCnt())
                .linkCnt(archiving.getLinkCnt())
                .scrapCnt(archiving.getScrapCnt())
                .markStatus(markStatus)
                .build();
    }
}
