package allchive.server.domain.config.async;

import static allchive.server.core.consts.AllchiveConst.*;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
@RequiredArgsConstructor
public class EnableAsyncConfig implements AsyncConfigurer {

    private final CustomAsyncExceptionHandler customAsyncExceptionHandler;

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return customAsyncExceptionHandler;
    }

    @Bean(name = "archivingContentCntTaskExecutor")
    public Executor archivingContentCntTaskExecutor() {
        return createTaskExecutor("ARCHIVING_CONTENT_CNT_TASK_EXECUTOR");
    }

    @Bean(name = "tagTaskExecutor")
    public Executor tagTaskExecutor() {
        return createTaskExecutor("TAG_TASK_EXECUTOR");
    }

    @Bean(name = "s3ImageTaskExecutor")
    public Executor s3ImageTaskExecutor() {
        return createTaskExecutor("S3_IMAGE_TASK_EXECUTOR");
    }

    @Bean(name = "slackTaskExecutor")
    public Executor slackTaskExecutor() {
        return createTaskExecutor("SLACK_TASK_EXECUTOR");
    }

    private Executor createTaskExecutor(String name) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.setThreadNamePrefix(name);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}
