package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Tag;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface TagCustomRepository {
    List<Tag> queryTagByUserIdOrderByUsedAt(Long userId);

    List<Tag> queryTagByTagIdIn(List<Long> tagIds);

    List<Tag> queryTagByUserIdContainName(Long userId, String name);

    Slice<Tag> querySliceTag(Pageable pageable);
}
