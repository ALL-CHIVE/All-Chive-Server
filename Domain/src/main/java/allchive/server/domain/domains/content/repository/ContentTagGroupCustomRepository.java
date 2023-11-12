package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.domain.Tag;
import java.util.List;

public interface ContentTagGroupCustomRepository {
    public List<ContentTagGroup> queryContentTagGroupByContentIn(List<Content> contentList);

    List<ContentTagGroup> queryContentTagGroupByContentWithTag(Content content);

    List<ContentTagGroup> queryContentTagGroupByTagInWithContent(List<Tag> tags);

    List<ContentTagGroup> queryContentTagGroupByTagIdInWithContent(List<Long> tagIds);
}
