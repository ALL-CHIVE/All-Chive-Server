package allchive.server.domain.domains.content.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import java.util.List;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ContentDomainService {
    private final ContentAdaptor contentAdaptor;

    public void save(Content content) {
        contentAdaptor.save(content);
    }

    public void softDeleteById(Long contentId) {
        Content content = contentAdaptor.findById(contentId);
        content.delete();
        save(content);
    }

    public void restoreByIdIn(List<Long> contentIds) {
        List<Content> contentList = contentAdaptor.findAllByIdIn(contentIds);
        contentList.forEach(Content::restore);
        contentAdaptor.saveAll(contentList);
    }

    public void deleteAllById(List<Long> contentIds) {
        contentAdaptor.deleteAllById(contentIds);
    }

    public void deleteAllByArchivingIdIn(List<Long> archivingId) {
        contentAdaptor.deleteAllByArchivingIdIn(archivingId);
    }

    public void update(
            Long contentId,
            Long archivingId,
            String link,
            String memo,
            String imgUrl,
            String title) {
        Content content = contentAdaptor.findById(contentId);
        content.updateContent(archivingId, imgUrl, link, title, memo);
        contentAdaptor.save(content);
    }
}
