package allchive.domain;

import allchive.server.ApiApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ApiApplication.class
})
public class DomainIntegrationTestConfiguration {

}