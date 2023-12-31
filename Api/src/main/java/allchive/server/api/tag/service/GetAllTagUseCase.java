package allchive.server.api.tag.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.tag.model.dto.response.AllTagResponse;
import allchive.server.api.tag.model.mapper.TagMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetAllTagUseCase {
    private final TagAdaptor tagAdaptor;
    private final TagMapper tagMapper;

    @Transactional(readOnly = true)
    public AllTagResponse execute(Boolean latestStatus) {
        List<Tag> tags = getTagList(latestStatus);
        return tagMapper.toAllTagResponse(tags);
    }

    private List<Tag> getTagList(Boolean latestStatus) {
        Long userId = SecurityUtil.getCurrentUserId();
        if (latestStatus) {
            return tagAdaptor.queryTagByUserIdOrderByUsedAt(userId);
        }
        return tagAdaptor.findAllByUserIdOrderByCreatedAtDesc(userId);
    }
}
