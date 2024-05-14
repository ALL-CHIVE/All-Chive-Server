package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ContentCustomRepository {
    Slice<Content> querySliceContentByArchivingIdAndContentTypeAndIdIn(
            Long archivingId, Pageable pageable, ContentType contentType, List<Long> contentIds);

    List<Content> queryContentInArchivingIds(List<Long> archivingIds);

    boolean queryContentExistById(Long id);
}
