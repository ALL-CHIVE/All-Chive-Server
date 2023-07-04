package allchive.server.api.category.model.vo;


import allchive.server.domain.domains.category.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TitleContentCntVo {
    @Schema(defaultValue = "카테고리 제목", description = "카테고리 제목")
    private String title;

    @Schema(defaultValue = "1", description = "카테고리에 속한 컨텐츠 총 개수")
    private Long contentCnt;

    @Builder
    private TitleContentCntVo(String title, Long contentCnt) {
        this.title = title;
        this.contentCnt = contentCnt;
    }

    public static TitleContentCntVo from(Category category) {
        return TitleContentCntVo.builder()
                .contentCnt(category.getImgCnt() + category.getScrapCnt())
                .title(category.getTitle())
                .build();
    }
}
