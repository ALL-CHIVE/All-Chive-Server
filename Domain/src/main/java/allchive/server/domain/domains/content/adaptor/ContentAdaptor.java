package allchive.server.domain.domains.content.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.exception.exceptions.ContentNotFoundException;
import allchive.server.domain.domains.content.repository.ContentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Adaptor
@RequiredArgsConstructor
public class ContentAdaptor {
    private final ContentRepository contentRepository;

    public Slice<Content> querySliceContentByArchivingId(Long archivingId, Pageable pageable) {
        return contentRepository.querySliceContentByArchivingId(archivingId, pageable);
    }

    public void save(Content content) {
        contentRepository.save(content);
    }

    public Content findById(Long contentId) {
        return contentRepository
                .findById(contentId)
                .orElseThrow(() -> ContentNotFoundException.EXCEPTION);
    }

    public void deleteById(Long contentId) {
        contentRepository.deleteById(contentId);
    }

    public List<Content> findAllByIdIn(List<Long> contentIdList) {
        return contentRepository.findAllByIdIn(contentIdList);
    }

    public void saveAll(List<Content> contentList) {
        contentRepository.saveAll(contentList);
    }

    public void deleteAllById(List<Long> contentIds) {
        contentRepository.deleteAllById(contentIds);
    }

    public List<Content> findAllByArchivingIds(List<Long> archivingIds) {
        return contentRepository.queryContentInArchivingIds(archivingIds);
    }

    public boolean queryContentExistById(Long contentId) {
        return contentRepository.queryContentExistById(contentId);
    }

    public void deleteAllByArchivingIdIn(List<Long> archivingId) {
        contentRepository.deleteAllByArchivingIdIn(archivingId);
    }
}
