package allchive.server.api.recycle.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.dto.request.ClearDeletedObjectRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.content.validator.ContentValidator;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import allchive.server.domain.domains.recycle.validator.RecycleValidator;
import allchive.server.domain.domains.user.service.ScrapDomainService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class ClearDeletedObjectUseCase {
    private final RecycleValidator recycleValidator;
    private final ArchivingValidator archivingValidator;
    private final ContentValidator contentValidator;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupDomainService contentTagGroupDomainService;
    private final ScrapDomainService scrapDomainService;
    private final ArchivingDomainService archivingDomainService;
    private final ContentDomainService contentDomainService;
    private final RecycleDomainService recycleDomainService;

    // TODO: report 지우기
    @Transactional
    public void execute(ClearDeletedObjectRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        recycleValidator.validateExist(request.getArchivingIds(), request.getContentIds(), userId);
        archivingValidator.verifyUserInIdList(userId, request.getArchivingIds());
        contentValidator.verifyUserInIdList(userId, request.getContentIds());
        List<Content> contents = contentAdaptor.findAllByArchivingIds(request.getArchivingIds());
        List<Long> contentsId = contents.stream().map(Content::getId).toList();
        if (!request.getContentIds().isEmpty()) {
            if (contentsId.isEmpty()) {
                contentsId = new ArrayList<>();
            }
            contentsId.addAll(request.getContentIds());
        }
        scrapDomainService.deleteAllByArchivingIdIn(request.getArchivingIds());
        contentTagGroupDomainService.deleteByContentIn(contents);
        contentDomainService.deleteAllById(contentsId);
        archivingDomainService.deleteAllById(request.getArchivingIds());
        recycleDomainService.deleteAllByUserIdAndArchivingIdOrUserIdAndContentId(
                request.getArchivingIds(), request.getContentIds(), userId);
    }
}
