package allchive.server.api.archiving.model.dto.request;


import allchive.server.core.annotation.ValidEnum;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UpdateArchivingRequest {
    @Schema(defaultValue = "아카이빙 제목", description = "아카이빙 제목")
    private String title;

    @Schema(defaultValue = "아카이빙 이미지 url", description = "아카이빙 이미지 url")
    private String imageUrl;

    @Schema(defaultValue = "DESIGN", description = "주제")
    @ValidEnum(target = Category.class)
    private Category category;

    @Schema(defaultValue = "false", description = "공개 여부")
    private boolean publicStatus;
}
