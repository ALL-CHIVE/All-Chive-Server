package allchive.server.domain.domains.content.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.exception.exceptions.TagNotFoundException;
import allchive.server.domain.domains.content.repository.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Adaptor
@RequiredArgsConstructor
public class TagAdaptor {
    private final TagRepository tagRepository;

    public List<Tag> queryTagByUserIdOrderByUsedAt(Long userId) {
        return tagRepository.queryTagByUserIdOrderByUsedAt(userId);
    }

    public List<Tag> findAllByUserIdOrderByCreatedAtDesc(Long userId) {
        return tagRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    public Tag findById(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> TagNotFoundException.EXCEPTION);
    }

    public void deleteById(Long tagId) {
        tagRepository.deleteById(tagId);
    }

    public List<Tag> queryTagByTagIdIn(List<Long> tagIds) {
        return tagRepository.queryTagByTagIdIn(tagIds);
    }

    public List<Tag> findAllByUserId(Long userId) {
        return tagRepository.findAllByUserId(userId);
    }

    public void deleteAll(List<Tag> tagList) {
        tagRepository.deleteAll(tagList);
    }

    public List<Tag> queryTagByUserIdContainName(Long userId, String word) {
        return tagRepository.queryTagByUserIdContainName(userId, word);
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Slice<Tag> querySliceTag(Pageable pageable) {
        return tagRepository.querySliceTag(pageable);
    }
}
