package allchive.server.domain.domains.archiving.service;

import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

import static allchive.server.core.consts.AllchiveConst.MINUS_ONE;
import static allchive.server.core.consts.AllchiveConst.PLUS_ONE;

@DomainService
@RequiredArgsConstructor
public class ArchivingAsyncDomainService {
    private final ArchivingDomainService archivingDomainService;

    @Async(value = "archivingContentCntTaskExecutor")
    public void updateContentCnt(Long archivingId, ContentType contentType, int i) {
        archivingDomainService.updateContentCnt(archivingId, contentType, i);
    }
}
