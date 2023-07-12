package allchive.server.api.content.model.mapper;


import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.content.model.dto.response.ContentResponse;
import allchive.server.api.content.model.dto.response.ContentTagResponse;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.api.tag.model.dto.response.TagResponse;
import allchive.server.domain.domains.content.domain.Tag;

import java.util.List;
import java.util.stream.Collectors;

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

    public Content toEntity(CreateContentRequest request) {
        return Content.of(
                request.getArchivingId(),
                request.getContentType(),
                UrlUtil.convertUrlToKey(request.getImgUrl()),
                request.getLink(),
                request.getTitle(),
                request.getMemo());
    }

    public ContentTagResponse toContentTagResponse(
            Content content, List<ContentTagGroup> contentTagGroupList) {
        List<TagResponse> tagResponseList =
                contentTagGroupList.stream()
                        .map(contentTagGroup -> TagResponse.from(contentTagGroup.getTag()))
                        .toList();
        return ContentTagResponse.of(content, tagResponseList);
    }

    public List<ContentTagGroup> toContentTagGroupEntityList(Content content, List<Tag> tags) {
        return tags.stream().map(tag -> ContentTagGroup.of(content, tag)).toList();
    }
}
