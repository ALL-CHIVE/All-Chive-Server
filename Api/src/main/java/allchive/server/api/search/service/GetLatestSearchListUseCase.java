package allchive.server.api.search.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.search.model.dto.response.SearchVoListResponse;
import allchive.server.api.search.model.vo.LatestSearchVo;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.search.adaptor.LatestSearchAdaptor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetLatestSearchListUseCase {
    private final LatestSearchAdaptor latestSearchAdaptor;

    @Transactional(readOnly = true)
    public SearchVoListResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<LatestSearchVo> keywords =
                latestSearchAdaptor.findAllByUserIdOrderByCreatedAt(userId).stream()
                        .map(LatestSearchVo::from)
                        .toList();
        return SearchVoListResponse.from(keywords);
    }
}
