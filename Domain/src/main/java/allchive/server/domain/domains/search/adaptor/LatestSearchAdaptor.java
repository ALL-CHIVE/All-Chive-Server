package allchive.server.domain.domains.search.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.search.domain.LatestSearch;
import allchive.server.domain.domains.search.repository.LatestSearchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class LatestSearchAdaptor {
    private final LatestSearchRepository latestSearchRepository;

    public List<LatestSearch> findAllByUserIdOrderByCreatedAt(Long userId) {
        return latestSearchRepository.findAllByUserIdOrderByCreatedAt(userId);
    }

    public void delete(LatestSearch latestSearch) {
        latestSearchRepository.delete(latestSearch);
    }

    public void save(LatestSearch newSearch) {
        latestSearchRepository.save(newSearch);
    }
}
