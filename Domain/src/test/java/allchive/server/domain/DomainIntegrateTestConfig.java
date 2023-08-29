package allchive.server.domain;


import allchive.server.core.CoreApplication;
import allchive.server.infrastructure.InfrastructureApplication;
import org.springframework.context.annotation.ComponentScan;

/** 스프링 부트 설정의 컴포넌트 스캔범위를 지정 통합 테스트를 위함 */
@ComponentScan(
        basePackageClasses = {
            InfrastructureApplication.class,
            DomainApplication.class,
            CoreApplication.class
        })
public class DomainIntegrateTestConfig {}
