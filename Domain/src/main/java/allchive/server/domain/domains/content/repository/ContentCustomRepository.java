package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ContentCustomRepository {
    Slice<Content> querySliceContentByCategoryId(Long categoryId, Pageable pageable);
}
