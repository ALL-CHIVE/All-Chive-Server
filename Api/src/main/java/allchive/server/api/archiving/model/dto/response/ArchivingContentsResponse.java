package allchive.server.api.archiving.model.dto.response;


import allchive.server.api.common.slice.SliceResponse;
import allchive.server.api.content.model.dto.response.ContentResponse;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import allchive.server.domain.domains.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArchivingContentsResponse {
    private SliceResponse<ContentResponse> contents;

    @Schema(description = "아카이빙 제목")
    private String archivingTitle;

    @Schema(description = "아카이빙 카테고리")
    private Category category;

    @Schema(description = "아카이빙 고유번호")
    private Long archivingId;

    @Schema(description = "아카이빙 생성일")
    private LocalDateTime createdAt;

    @Schema(description = "아카이빙의 총 컨텐츠 개수")
    private Long totalContentsCount;

    @Schema(description = "아카이빙 소유자 고유번호")
    private Long ownerId;

    @Schema(description = "아카이빙 소유자 닉네임")
    private String ownerNickname;

    @Schema(description = "아카이빙 소유자 프로픨 이미지")
    private String ownerProfileImgUrl;

    @Schema(description = "유저 소유 여부")
    private Boolean isMine;

    @Schema(description = "아카이빙 스크랩 여부")
    private Boolean isScrap;

    @Builder
    private ArchivingContentsResponse(
            SliceResponse<ContentResponse> contents,
            String archivingTitle,
            Category category,
            Long archivingId,
            LocalDateTime createdAt,
            Long totalContentsCount,
            Long ownerId,
            String ownerNickname,
            String ownerProfileImgUrl,
            Boolean isMine,
            Boolean isScrap) {
        this.contents = contents;
        this.archivingTitle = archivingTitle;
        this.category = category;
        this.archivingId = archivingId;
        this.createdAt = createdAt;
        this.totalContentsCount = totalContentsCount;
        this.ownerId = ownerId;
        this.ownerNickname = ownerNickname;
        this.ownerProfileImgUrl = ownerProfileImgUrl;
        this.isMine = isMine;
        this.isScrap = isScrap;
    }

    public static ArchivingContentsResponse of(
            SliceResponse<ContentResponse> contentResponseSlice,
            Archiving archiving,
            User user,
            Boolean isMine,
            Boolean isScrap) {
        return ArchivingContentsResponse.builder()
                .archivingId(archiving.getId())
                .createdAt(archiving.getCreatedAt())
                .archivingTitle(archiving.getTitle())
                .category(archiving.getCategory())
                .totalContentsCount(archiving.getScrapCnt() + archiving.getImgCnt())
                .contents(contentResponseSlice)
                .ownerId(user.getId())
                .ownerNickname(user.getNickname())
                .ownerProfileImgUrl(user.getProfileImgUrl())
                .isMine(isMine)
                .isScrap(isScrap)
                .build();
    }
}
