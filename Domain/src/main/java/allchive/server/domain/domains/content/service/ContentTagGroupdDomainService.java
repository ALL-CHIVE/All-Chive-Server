package allchive.server.domain.domains.content.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.content.adaptor.ContentTagGroupAdaptor;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ContentTagGroupdDomainService {
    private final ContentTagGroupAdaptor contentTagGroupAdaptor;

    public void deleteById(Tag tag) {
        contentTagGroupAdaptor.deleteByTag(tag);
    }
}
