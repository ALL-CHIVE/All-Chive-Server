package allchive.server.api.archiving.model.vo;


import allchive.server.domain.domains.archiving.domain.Archiving;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TitleContentCntVo {
    @Schema(defaultValue = "아카이빙 제목", description = "아카이빙 제목")
    private String title;

    @Schema(defaultValue = "1", description = "아카이빙에 속한 컨텐츠 총 개수")
    private Long contentCnt;

    @Builder
    private TitleContentCntVo(String title, Long contentCnt) {
        this.title = title;
        this.contentCnt = contentCnt;
    }

    public static TitleContentCntVo from(Archiving archiving) {
        return TitleContentCntVo.builder()
                .contentCnt(archiving.getImgCnt() + archiving.getScrapCnt())
                .title(archiving.getTitle())
                .build();
    }
}
