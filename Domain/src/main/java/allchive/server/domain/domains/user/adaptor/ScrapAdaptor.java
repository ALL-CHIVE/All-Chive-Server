package allchive.server.domain.domains.user.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.exception.exceptions.ScrapNotFoundException;
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

    public Scrap findByUserAndArchivingId(User user, Long archivingId) {
        return scrapRepository
                .findAllByUserAndArchivingId(user, archivingId)
                .orElseThrow(() -> ScrapNotFoundException.EXCEPTION);
    }

    public void delete(Scrap scrap) {
        scrapRepository.delete(scrap);
    }

    public void save(Scrap scrap) {
        scrapRepository.save(scrap);
    }

    public void deleteAllByArchivingIdIn(List<Long> archivingIds) {
        scrapRepository.deleteAllByArchivingIdIn(archivingIds);
    }

    public void deleteAllByUser(User user) {
        scrapRepository.deleteAllByUser(user);
    }
}
