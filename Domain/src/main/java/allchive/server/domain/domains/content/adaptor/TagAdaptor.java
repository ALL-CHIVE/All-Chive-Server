package allchive.server.domain.domains.content.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.repository.TagRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class TagAdaptor {
    private final TagRepository tagRepository;

    public List<Tag> findAllByUserIdOrderByCreatedAtDesc(Long userId) {
        return tagRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    public List<Tag> queryTagByUserIdOrderByUsedAt(Long userId) {
        return tagRepository.queryTagByUserIdOrderByUsedAt(userId);
    }
}
