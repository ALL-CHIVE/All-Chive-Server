package allchive.server.infrastructure.s3.service;


import allchive.server.core.error.exception.S3ObjectNotFoundException;
import allchive.server.core.event.events.s3.S3ImageDeleteEvent;
import com.amazonaws.services.s3.AmazonS3;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class S3DeleteObjectService {
    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Async(value = "s3ImageTaskExecutor")
    @TransactionalEventListener(value = S3ImageDeleteEvent.class
            ,phase = TransactionPhase.AFTER_COMMIT)
    public void deleteS3Object(S3ImageDeleteEvent s3ImageDeleteEvent) {
        s3ImageDeleteEvent.getKeys().forEach(
                key -> {
                    validateExistObject(key);
                    amazonS3.deleteObject(bucket, key);
                });
    }

    private void validateExistObject(String key) {
        if (!amazonS3.doesObjectExist(bucket, key)) {
            throw S3ObjectNotFoundException.EXCEPTION;
        }
    }
}
