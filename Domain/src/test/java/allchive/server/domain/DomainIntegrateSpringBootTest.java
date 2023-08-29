package allchive.server.domain;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

/** 도메인 모듈의 통합테스트의 편의성을 위해서 만든 어노테이션 -이찬진 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = DomainIntegrateTestConfig.class)
@ActiveProfiles(resolver = DomainIntegrateProfileResolver.class)
@Documented
public @interface DomainIntegrateSpringBootTest {}
