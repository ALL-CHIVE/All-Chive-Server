package allchive.server.api.search.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.search.model.dto.request.DeleteLatestSearchRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.search.service.LatestSearchDomainService;
import allchive.server.domain.domains.search.validator.LatestSearchValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteLatestSearchUseCase {
    private final LatestSearchValidator latestSearchValidator;
    private final LatestSearchDomainService latestSearchDomainService;

    @Transactional
    public void execute(DeleteLatestSearchRequest request) {
        validateExecution(request.getIds());
        latestSearchDomainService.deleteAllByIdIn(request.getIds());
    }

    private void validateExecution(List<Long> ids) {
        Long userId = SecurityUtil.getCurrentUserId();
        latestSearchValidator.validateExistByIdIn(ids);
        latestSearchValidator.verifyUserByIdIn(ids, userId);
    }
}
