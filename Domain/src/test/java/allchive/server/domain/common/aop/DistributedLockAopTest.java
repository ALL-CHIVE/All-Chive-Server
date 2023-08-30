package allchive.server.domain.common.aop;

import static org.assertj.core.api.Assertions.assertThat;

import allchive.server.domain.CalculateCurrentExecutionSupporter;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.aop.distributedLock.DistributedLockAop;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.infrastructure.redis.lock.LockManager;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DistributedLockAopTest {
    @MockBean LockManager lockManager;
    DistributedLockAop distributedLockAop;

    private final RedissonService redissonService;

    @Autowired
    public DistributedLockAopTest(RedissonService redissonService) {
        this.redissonService = redissonService;
    }

    @BeforeEach
    public void beforeEach() {
        distributedLockAop = new DistributedLockAop(lockManager);
    }

    @Test
    void 분산락_적용_동시성_테스트() throws InterruptedException {
        AtomicLong successCount = new AtomicLong();
        CalculateCurrentExecutionSupporter.execute(
                () -> redissonService.testWithLock(1), successCount);
        assertThat(redissonService.apply).isEqualTo((int) successCount.get());
    }

    @Test
    void 분산락_미적용_동시성_테스트() throws InterruptedException {
        AtomicLong successCount = new AtomicLong();
        CalculateCurrentExecutionSupporter.execute(
                () -> redissonService.testWithoutLock(1), successCount);
        assertThat(redissonService.apply).isNotEqualTo((int) successCount.get());
    }
}

@Service
class RedissonService {
    int apply = 0;

    @DistributedLock(lockType = DistributedLockType.ARCHIVING_SCRAP, identifier = "archivingId")
    void testWithLock(int id) {
        apply++;
    }

    void testWithoutLock(int id) {
        apply++;
    }
}
