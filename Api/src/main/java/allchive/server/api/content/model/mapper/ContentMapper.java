package allchive.server.api.content.model.mapper;


import allchive.server.api.content.model.dto.response.ContentResponse;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import java.util.List;

@Mapper
public class ContentMapper {
    public ContentResponse toContentResponse(
            Content content, List<ContentTagGroup> contentTagGroupList) {
        List<ContentTagGroup> tags =
                contentTagGroupList.stream()
                        .filter(contentTagGroup -> contentTagGroup.getContent().equals(content))
                        .toList();
        ContentTagGroup contentTagGroup = tags.stream().findFirst().orElse(null);
        String tag = contentTagGroup == null ? null : contentTagGroup.getTag().getName();
        return ContentResponse.of(content, tag, (long) tags.size());
    }
}
