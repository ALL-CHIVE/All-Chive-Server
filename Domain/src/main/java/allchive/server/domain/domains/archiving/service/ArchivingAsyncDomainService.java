package allchive.server.domain.domains.archiving.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

@DomainService
@RequiredArgsConstructor
public class ArchivingAsyncDomainService {
    private final ArchivingDomainService archivingDomainService;

    @Async(value = "archivingContentCntTaskExecutor")
    @DistributedLock(lockType = DistributedLockType.ARCHIVING, identifier ={"archivingId"})
    public void updateContentCnt(Long archivingId, ContentType contentType, int i) {
        archivingDomainService.updateContentCnt(archivingId, contentType, i);
    }
}
