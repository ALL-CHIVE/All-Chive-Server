package allchive.server.domain.domains.content.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ContentAdaptor {
    private final ContentRepository contentRepository;

    public Slice<Content> querySliceContentByCategoryId(Long categoryId, Pageable pageable) {
        return contentRepository.querySliceContentByCategoryId(categoryId, pageable);
    }
}
