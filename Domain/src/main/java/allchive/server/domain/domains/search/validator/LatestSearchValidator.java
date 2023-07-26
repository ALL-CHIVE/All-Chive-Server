package allchive.server.domain.domains.search.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.search.adaptor.LatestSearchAdaptor;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class LatestSearchValidator {
    private final LatestSearchAdaptor latestSearchAdaptor;

    public void validateExistByIdAndUserId(Long latestSearchId, Long userId) {
        latestSearchAdaptor.findByIdAndUserId(latestSearchId, userId);
    }
}
