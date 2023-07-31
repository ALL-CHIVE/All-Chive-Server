package allchive.server.infrastructure.s3.service;


import allchive.server.core.error.exception.S3ObjectNotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3DeleteObjectService {
    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public void deleteS3Object(List<String> keys) {
        keys.forEach(
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
