package allchive.server.api.search.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.search.model.dto.response.SearchListResponse;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.search.adaptor.LatestSearchAdaptor;
import allchive.server.domain.domains.search.domain.LatestSearch;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetLatestSearchListUseCase {
    private final LatestSearchAdaptor latestSearchAdaptor;

    @Transactional(readOnly = true)
    public SearchListResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<String> keywords =
                latestSearchAdaptor.findAllByUserIdOrderByCreatedAt(userId).stream()
                        .map(LatestSearch::getKeyword)
                        .toList();
        return SearchListResponse.from(keywords);
    }
}
