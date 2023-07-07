package allchive.server.api.content.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.validator.CategoryValidator;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.service.ContentDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateContentUseCase {
    private final CategoryValidator categoryValidator;
    private final ContentMapper contentMapper;
    private final ContentDomainService contentDomainService;

    // TODO : tag 만들면 연결
    @Transactional
    public void execute(CreateContentRequest request) {
        categoryValidator.validateExistCategory(request.getCategoryId());
        Long userId = SecurityUtil.getCurrentUserId();
        categoryValidator.validateCategoryUser(request.getCategoryId(), userId);
        Content content = contentMapper.toEntity(request);
        contentDomainService.save(content);
    }
}
