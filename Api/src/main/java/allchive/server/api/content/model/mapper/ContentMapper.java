package allchive.server.api.content.model.mapper;

import allchive.server.api.content.model.dto.response.ContentResponse;
import allchive.server.core.annotation.Mapper;
import allchive.server.core.error.exception.InternalServerError;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.domain.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class ContentMapper {
    public ContentResponse toContentResponse(Content content, List<ContentTagGroup> contentTagGroupList) {
        List<ContentTagGroup> tags = contentTagGroupList.stream()
                .filter(contentTagGroup -> contentTagGroup.getContent().equals(content))
                .toList();
        ContentTagGroup contentTagGroup = tags.stream().findFirst().orElse(null);
        String tag = contentTagGroup == null ? null : contentTagGroup.getTag().getName();
        return ContentResponse.of(content, tag, (long) tags.size());
    }
}
