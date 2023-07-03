package allchive.server.domain.domains.user.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import allchive.server.domain.domains.user.repository.ScrapRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ScrapAdaptor {
    private final ScrapRepository scrapRepository;

    public List<Scrap> findAllByUserId(Long userId) {
        return scrapRepository.findAllByUserId(userId);
    }
}
