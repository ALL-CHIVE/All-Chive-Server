package allchive.server.api.archiving.service;


import allchive.server.api.archiving.model.dto.response.ArchivingTitleResponse;
import allchive.server.api.archiving.model.mapper.ArchivingMapper;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetArchivingTitleUseCase {
    private final ArchivingAdaptor archivingAdaptor;
    private final ArchivingMapper archivingMapper;

    @Transactional(readOnly = true)
    public ArchivingTitleResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        return archivingMapper.toArchivingTitleResponse(
                archivingAdaptor.queryArchivingByUserId(userId));
    }
}
