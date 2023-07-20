package allchive.server.domain.domains.user.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import allchive.server.domain.domains.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ScrapDomainService {
    private final ScrapAdaptor scrapAdaptor;

    public void deleteScrapByUserAndArchivingId(User user, Long archivingId) {
        Scrap scrap = scrapAdaptor.findByUserAndArchivingId(user, archivingId);
        scrapAdaptor.delete(scrap);
    }

    public void save(Scrap scrap) {
        scrapAdaptor.save(scrap);
    }

    public void deleteAllByArchivingIdIn(List<Long> archivingIds) {
        scrapAdaptor.deleteAllByArchivingIdIn(archivingIds);
    }

    public void deleteAllByUser(User user) {
        scrapAdaptor.deleteAllByUser(user);
    }
}
