package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ContentCustomRepository {
    Slice<Content> querySliceContentByArchivingId(Long archivingId, Pageable pageable);

    List<Content> queryContentInArchivingIds(List<Long> archivingIds);

    boolean queryContentExistById(Long id);
}
