package allchive.server.domain.domains.content.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TagDomainService {
    private final TagAdaptor tagAdaptor;

    public void save(Tag tag) {
        tagAdaptor.save(tag);
    }

    public void updateTag(Long tagId, String name) {
        Tag tag = tagAdaptor.findById(tagId);
        tag.updateName(name);
        tagAdaptor.save(tag);
    }

    public void deleteById(Long tagId) {
        tagAdaptor.deleteById(tagId);
    }

    public void deleteAll(List<Tag> tagList) {
        tagAdaptor.deleteAll(tagList);
    }

    public void updateUsedAt(Tag tag) {
        tag.updateUsedAt(LocalDateTime.now());
        tagAdaptor.save(tag);
    }
}
