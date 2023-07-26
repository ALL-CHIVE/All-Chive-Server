package allchive.server.domain.domains.search.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.search.adaptor.LatestSearchAdaptor;
import allchive.server.domain.domains.search.domain.LatestSearch;
import allchive.server.domain.domains.search.exception.exceptions.LatestSearchNotFoundException;
import allchive.server.domain.domains.search.exception.exceptions.NoAuthorityUpdateLatestSearchException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class LatestSearchValidator {
    private final LatestSearchAdaptor latestSearchAdaptor;

    public void validateExistByIdIn(List<Long> ids) {
        List<LatestSearch> searches = latestSearchAdaptor.findAllByIdIn(ids);
        if (searches.size() != ids.size()) {
            throw LatestSearchNotFoundException.EXCEPTION;
        }
    }

    public void verifyUserByIdIn(List<Long> ids, Long userId) {
        List<LatestSearch> searches = latestSearchAdaptor.findAllByIdIn(ids);
        searches.forEach(
                search -> {
                    if (!search.getUserId().equals(userId)) {
                        throw NoAuthorityUpdateLatestSearchException.EXCEPTION;
                    }
                });
    }
}
