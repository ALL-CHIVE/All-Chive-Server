package allchive.server.domain.domains.user.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.repository.ScrapRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ScrapAdaptor {
    private final ScrapRepository scrapRepository;

    public List<Scrap> findAllByUserId(Long userId) {
        return scrapRepository.findAllByUserId(userId);
    }

    public Optional<Scrap> findByUserAndCategoryId(User user, Long categoryId) {
        return scrapRepository.findAllByUserAndCategoryId(user, categoryId);
    }

    public void delete(Scrap scrap) {
        scrapRepository.delete(scrap);
    }

    public void save(Scrap scrap) {
        scrapRepository.save(scrap);
    }
}
