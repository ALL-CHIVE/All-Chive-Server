package allchive.server.api.tag.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.tag.model.dto.request.CreateTagRequest;
import allchive.server.api.tag.model.dto.response.TagResponse;
import allchive.server.api.tag.model.mapper.TagMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.service.TagDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateTagUseCase {
    private final TagMapper tagMapper;
    private final TagDomainService tagDomainService;

    @Transactional
    public TagResponse execute(CreateTagRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        Tag tag = tagMapper.toEntity(request, userId);
        tagDomainService.save(tag);
        return TagResponse.from(tag);
    }
}
