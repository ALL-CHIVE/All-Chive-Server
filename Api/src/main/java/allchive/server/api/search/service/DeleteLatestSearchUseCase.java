package allchive.server.api.search.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.search.adaptor.LatestSearchAdaptor;
import allchive.server.domain.domains.search.validator.LatestSearchValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteLatestSearchUseCase {
    private final LatestSearchValidator latestSearchValidator;
    private final LatestSearchAdaptor latestSearchAdaptor;

    @Transactional
    public void execute(Long latestSearchId) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(latestSearchId, userId);
        latestSearchAdaptor.deleteByIdAndUserId(latestSearchId, userId);
    }

    private void validateExecution(Long latestSearchId, Long userId) {
        latestSearchValidator.validateExistByIdAndUserId(latestSearchId, userId);
    }
}
