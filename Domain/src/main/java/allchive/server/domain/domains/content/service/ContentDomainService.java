package allchive.server.domain.domains.content.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ContentDomainService {
    private final ContentAdaptor contentAdaptor;

    public void save(Content content) {
        contentAdaptor.save(content);
    }

    public void deleteById(Long contentId) {
        contentAdaptor.deleteById(contentId);
    }
}
