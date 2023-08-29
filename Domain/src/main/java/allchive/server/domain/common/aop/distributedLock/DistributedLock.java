package allchive.server.domain.common.aop.distributedLock;


import allchive.server.domain.common.enums.DistributedLockType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    // 락 타입
    DistributedLockType lockType();

    // 분산락을 걸 파라미터 네임
    String[] identifier();
}
