package allchive.server.api.content.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.service.ContentDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateContentUseCase {
    private final ArchivingValidator archivingValidator;
    private final ContentMapper contentMapper;
    private final ContentDomainService contentDomainService;

    // TODO : tag 만들면 연결
    @Transactional
    public void execute(CreateContentRequest request) {
        archivingValidator.validateExistArchiving(request.getArchivingId());
        Long userId = SecurityUtil.getCurrentUserId();
        archivingValidator.validateArchivingUser(request.getArchivingId(), userId);
        Content content = contentMapper.toEntity(request);
        contentDomainService.save(content);
    }
}
