package allchive.server.domain.domains.content.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import java.util.List;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ContentDomainService {
    private final ContentAdaptor contentAdaptor;

    public void save(Content content) {
        contentAdaptor.save(content);
    }

    public void deleteById(Long contentId) {
        Content content = contentAdaptor.findById(contentId);
        content.delete();
        save(content);
    }

    public void restoreInIdList(List<Long> contentIds) {
        List<Content> contentList = contentAdaptor.findAllByIdIn(contentIds);
        contentList.forEach(Content::restore);
        contentAdaptor.saveAll(contentList);
    }
}
