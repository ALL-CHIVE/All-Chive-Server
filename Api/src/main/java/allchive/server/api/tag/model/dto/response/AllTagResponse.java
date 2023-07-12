package allchive.server.api.tag.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AllTagResponse {
    private List<TagResponse> tags;

    @Builder
    private AllTagResponse(List<TagResponse> tags) {
        this.tags = tags;
    }

    public static AllTagResponse from(List<TagResponse> tags) {
        return AllTagResponse.builder()
                .tags(tags)
                .build();
    }
}
