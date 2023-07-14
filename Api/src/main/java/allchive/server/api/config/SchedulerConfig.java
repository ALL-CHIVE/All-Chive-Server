package allchive.server.api.config;


import allchive.server.api.SchedulerPackageLocation;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EntityScan(basePackageClasses = SchedulerPackageLocation.class)
public class SchedulerConfig {}
