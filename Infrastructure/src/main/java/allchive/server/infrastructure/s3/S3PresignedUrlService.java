package allchive.server.infrastructure.s3;

import allchive.server.core.error.exception.InternalServerError;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class S3PresignedUrlService {
    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.s3.base-url}")
    private String baseUrl;

    public String getPreSignedUrl(Long id, PresignedType presignedType) {
        String fileName = generateFileName(id, presignedType);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePreSignedUrlRequest(fileName);
        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    private String generateFileName(Long id, PresignedType presignedType) {
        String fileName;
        switch (presignedType) {
            case USER -> fileName = baseUrl + "/user/";
            case CONTENT -> fileName = baseUrl + "/content/";
            case CATEGORY -> fileName = baseUrl + "/category/";
            default -> throw InternalServerError.EXCEPTION;
        }
        return fileName
                + id.toString()
                + "/"
                + UUID.randomUUID();
    }

    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(String fileName) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withKey(fileName)
                        .withExpiration(getPreSignedUrlExpiration());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString());
        return generatePresignedUrlRequest;
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        // 4분
        expTimeMillis += 1000 * 60 * 4;
        expiration.setTime(expTimeMillis);
        return expiration;
    }
}
