package allchive.server.api.tag.model.mapper;


import allchive.server.api.tag.model.dto.request.CreateTagRequest;
import allchive.server.api.tag.model.dto.response.AllTagResponse;
import allchive.server.api.tag.model.dto.response.TagResponse;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.content.domain.Tag;
import java.util.List;

@Mapper
public class TagMapper {
    public AllTagResponse toAllTagResponse(List<Tag> tagList) {
        List<TagResponse> tagResponseList = tagList.stream().map(TagResponse::from).toList();
        return AllTagResponse.from(tagResponseList);
    }

    public Tag toEntity(CreateTagRequest request, Long userId) {
        return Tag.of(request.getName(), userId);
    }
}
