package allchive.server.domain.domains.content.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.exception.exceptions.ContentNotFoundException;
import allchive.server.domain.domains.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Adaptor
@RequiredArgsConstructor
public class ContentAdaptor {
    private final ContentRepository contentRepository;

    public Slice<Content> querySliceContentByCategoryId(Long categoryId, Pageable pageable) {
        return contentRepository.querySliceContentByCategoryId(categoryId, pageable);
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
}
