package allchive.server.api.tag.model.dto.response;


import allchive.server.domain.domains.content.domain.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TagResponse {
    @Schema(description = "태그 고유번호")
    private Long tagId;

    @Schema(description = "태그 이름")
    private String name;

    @Builder
    private TagResponse(Long tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public static TagResponse from(Tag tag) {
        return TagResponse.builder().tagId(tag.getId()).name(tag.getName()).build();
    }
}
