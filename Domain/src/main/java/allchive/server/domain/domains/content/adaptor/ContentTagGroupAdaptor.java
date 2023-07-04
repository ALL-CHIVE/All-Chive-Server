package allchive.server.domain.domains.content.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.repository.ContentTagGroupRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ContentTagGroupAdaptor {
    private final ContentTagGroupRepository contentTagGroupRepository;

    public List<ContentTagGroup> queryContentIn(List<Content> contentList) {
        return contentTagGroupRepository.queryContentTagGroupIn(contentList);
    }
}
