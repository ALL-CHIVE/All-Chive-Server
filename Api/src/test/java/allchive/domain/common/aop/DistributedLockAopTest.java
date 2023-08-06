package allchive.domain.common.aop;


import allchive.domain.CalculateCurrentExecutionSupporter;
import allchive.domain.DomainIntegrationTest;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicLong;

import static allchive.server.core.consts.AllchiveConst.PLUS_ONE;
import static org.assertj.core.api.Assertions.assertThat;

@DomainIntegrationTest
//@SpringBootTest
//@DataJpaTest
//@ExtendWith(SpringExtension.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "local")
public class DistributedLockAopTest {
    @Mock
    ArchivingDomainService archivingDomainService;

    @Transactional
    @DistributedLock(lockType = DistributedLockType.ARCHIVING_SCRAP, identifier = "archivingId")
    public void scrapArchivingByIdWithLock(Long archivingId) {
        archivingDomainService.updateScrapCount(archivingId, PLUS_ONE);
    }

    @Transactional
    public void scrapArchivingByIdWithTransactional(Long archivingId) {
        archivingDomainService.updateScrapCount(archivingId, PLUS_ONE);
    }

    public void scrapArchivingByIdWithoutLock(Long archivingId) {
        archivingDomainService.updateScrapCount(archivingId, PLUS_ONE);
    }


    @Test
    @DisplayName("분산락 적용시 추천은 한번만 이루어져야 한다")
    public void distributedLockTest() throws InterruptedException {
        // when
        AtomicLong successCount = new AtomicLong();
        try {
            CalculateCurrentExecutionSupporter.execute(
                    () -> {
                        scrapArchivingByIdWithLock(1L);
                    },
                    successCount
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // then
        System.out.println(successCount);
        assertThat(successCount).isEqualTo(1L);
    }
}