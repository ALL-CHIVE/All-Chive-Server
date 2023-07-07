package allchive.server.api.content.model.dto.request;


import allchive.server.core.annotation.ValidEnum;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class CreateContentRequest {
    @Schema(defaultValue = "image", description = "컨텐츠 타입")
    @ValidEnum(target = ContentType.class)
    private ContentType contentType;

    @Schema(defaultValue = "0", description = "카테고리 고유번호")
    private Long categoryId;

    @Schema(defaultValue = "제목", description = "제목")
    private String title;

    @Schema(defaultValue = "링크", description = "링크")
    private String link;

    @Schema(defaultValue = "이미지 url", description = "이미지 url")
    private String imgUrl;

    @Schema(description = "태그 고유번호 리스트")
    private List<Long> tagIds;

    @Schema(defaultValue = "메모", description = "메모")
    private String memo;
}
