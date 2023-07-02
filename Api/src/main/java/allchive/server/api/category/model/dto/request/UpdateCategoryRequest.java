package allchive.server.api.category.model.dto.request;

import allchive.server.core.annotation.ValidEnum;
import allchive.server.domain.domains.category.domain.enums.Topic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UpdateCategoryRequest {
    @Schema(defaultValue = "카테고리 제목", description = "카테고리 제목")
    private String title;

    @Schema(defaultValue = "디자인", description = "주제")
    @ValidEnum(target = Topic.class)
    private Topic topic;

    @Schema(defaultValue = "false", description = "공개 여부")
    private boolean publicStatus;
}
