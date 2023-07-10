package allchive.server.api.archiving.model.dto.response;


import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.content.model.dto.response.ContentResponse;
import allchive.server.domain.domains.archiving.domain.Archiving;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArchivingContentsResponse {
    private SliceResponse<ContentResponse> contents;

    @Schema(description = "아카이빙 제목")
    private String archivingTitle;

    @Schema(description = "아카이빙 고유번호")
    private Long archivingId;

    @Schema(description = "아카이빙의 총 컨텐츠 개수")
    private Long totalContentsCount;

    @Builder
    private ArchivingContentsResponse(
            SliceResponse<ContentResponse> contents,
            String archivingTitle,
            Long archivingId,
            Long totalContentsCount) {
        this.contents = contents;
        this.archivingTitle = archivingTitle;
        this.archivingId = archivingId;
        this.totalContentsCount = totalContentsCount;
    }

    public static ArchivingContentsResponse of(
            SliceResponse<ContentResponse> contentResponseSlice, Archiving archiving) {
        return ArchivingContentsResponse.builder()
                .archivingId(archiving.getId())
                .archivingTitle(archiving.getTitle())
                .totalContentsCount(archiving.getScrapCnt() + archiving.getImgCnt())
                .contents(contentResponseSlice)
                .build();
    }
}