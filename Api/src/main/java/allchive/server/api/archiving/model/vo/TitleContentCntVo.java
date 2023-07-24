package allchive.server.api.archiving.model.vo;


import allchive.server.domain.domains.archiving.domain.Archiving;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TitleContentCntVo {
    @Schema(description = "아카이빙 고유 아이디")
    private Long archivingId;

    @Schema(defaultValue = "아카이빙 제목", description = "아카이빙 제목")
    private String title;

    @Schema(defaultValue = "1", description = "아카이빙에 속한 컨텐츠 총 개수")
    private Long contentCnt;

    @Builder
    private TitleContentCntVo(Long archivingId, String title, Long contentCnt) {
        this.archivingId = archivingId;
        this.title = title;
        this.contentCnt = contentCnt;
    }

    public static TitleContentCntVo from(Archiving archiving) {
        return TitleContentCntVo.builder()
                .archivingId(archiving.getId())
                .contentCnt(archiving.getImgCnt() + archiving.getScrapCnt())
                .title(archiving.getTitle())
                .build();
    }
}
