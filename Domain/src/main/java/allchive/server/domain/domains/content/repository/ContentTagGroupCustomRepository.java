package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import java.util.List;

public interface ContentTagGroupCustomRepository {
    public List<ContentTagGroup> queryContentTagGroupIn(List<Content> contentList);
}
