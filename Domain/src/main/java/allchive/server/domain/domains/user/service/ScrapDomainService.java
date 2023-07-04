package allchive.server.domain.domains.user.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.user.adaptor.ScrapAdaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.exception.exceptions.ScrapNotFoundException;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ScrapDomainService {
    private final ScrapAdaptor scrapAdaptor;

    public void deleteScrapByUserAndCategoryId(User user, Long categoryId) {
        Scrap scrap =
                scrapAdaptor
                        .findByUserAndCategoryId(user, categoryId)
                        .orElseThrow(() -> ScrapNotFoundException.EXCEPTION);
        ;
        scrapAdaptor.delete(scrap);
    }

    public void save(Scrap scrap) {
        scrapAdaptor.save(scrap);
    }
}
