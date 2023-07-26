package allchive.server.domain.domains.content.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.repository.ContentTagGroupRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ContentTagGroupAdaptor {
    private final ContentTagGroupRepository contentTagGroupRepository;

    public List<ContentTagGroup> queryContentTagGroupByContentIn(List<Content> contentList) {
        return contentTagGroupRepository.queryContentTagGroupByContentIn(contentList);
    }

    public void deleteByTag(Tag tag) {
        contentTagGroupRepository.deleteByTag(tag);
    }

    public void saveAll(List<ContentTagGroup> contentTagGroupList) {
        contentTagGroupRepository.saveAll(contentTagGroupList);
    }

    public void deleteAllByContentIn(List<Content> contents) {
        contentTagGroupRepository.deleteAllByContentIn(contents);
    }

    public void deleteAllByTagIn(List<Tag> tagList) {
        contentTagGroupRepository.deleteAllByTagIn(tagList);
    }

    public List<ContentTagGroup> queryContentTagGroupByContentWithTag(Content content) {
        return contentTagGroupRepository.queryContentTagGroupByContentWithTag(content);
    }

    public void deleteAllByContent(Content content) {
        contentTagGroupRepository.deleteAllByContent(content);
    }

    public List<ContentTagGroup> queryContentTagGroupByTagInWithContent(List<Tag> tags) {
        return contentTagGroupRepository.queryContentTagGroupByTagInWithContent(tags);
    }
}
