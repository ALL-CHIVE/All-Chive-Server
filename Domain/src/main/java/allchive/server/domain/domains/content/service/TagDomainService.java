package allchive.server.domain.domains.content.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TagDomainService {
    private final TagAdaptor tagAdaptor;

    public void save(Tag tag) {
        tagAdaptor.save(tag);
    }
}
