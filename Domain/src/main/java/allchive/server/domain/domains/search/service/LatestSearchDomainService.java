package allchive.server.domain.domains.search.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.search.adaptor.LatestSearchAdaptor;
import allchive.server.domain.domains.search.domain.LatestSearch;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class LatestSearchDomainService {
    private final LatestSearchAdaptor latestSearchAdaptor;

    public void delete(LatestSearch latestSearch) {
        latestSearchAdaptor.delete(latestSearch);
    }

    public void save(LatestSearch newSearch) {
        latestSearchAdaptor.save(newSearch);
    }

    public void deleteAllByUserId(Long userId) {
        latestSearchAdaptor.deleteAllByUserId(userId);
    }
}
