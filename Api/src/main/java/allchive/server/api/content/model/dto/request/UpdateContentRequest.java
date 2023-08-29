package allchive.server.api.content.model.dto.request;


import allchive.server.core.annotation.ValidEnum;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class UpdateContentRequest {
    @Schema(defaultValue = "IMAGE", description = "컨텐츠 타입")
    @ValidEnum(target = ContentType.class)
    private ContentType contentType;

    @Positive
    @Schema(defaultValue = "0", description = "아카이빙 고유번호")
    private Long archivingId;

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
