package allchive.server.api.image.controller;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.image.model.dto.response.ImageUrlResponse;
import allchive.server.infrastructure.s3.PresignedType;
import allchive.server.infrastructure.s3.S3PresignedUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "a. [image]")
public class ImageController {
    private final S3PresignedUrlService s3PresignedUrlService;

    @Operation(summary = "카테고리 관련 이미지 업로드 url 요청할수 있는 api 입니다.")
    @GetMapping(value = "/archivings/image")
    public ImageUrlResponse getArchivingPresignedUrl() {
        Long userId = SecurityUtil.getCurrentUserId();
        return ImageUrlResponse.from(
                s3PresignedUrlService.getPreSignedUrl(userId, PresignedType.ARCHIVING));
    }

    @Operation(summary = "컨텐츠 관련 이미지 업로드 url 요청할수 있는 api 입니다.")
    @GetMapping(value = "/contents/image")
    public ImageUrlResponse getContentPresignedUrl() {
        Long userId = SecurityUtil.getCurrentUserId();
        return ImageUrlResponse.from(
                s3PresignedUrlService.getPreSignedUrl(userId, PresignedType.CONTENT));
    }

    @Operation(summary = "컨텐츠 관련 이미지 업로드 url 요청할수 있는 api 입니다.")
    @GetMapping(value = "/user/image")
    public ImageUrlResponse getUserPresignedUrl() {
        Long userId = SecurityUtil.getCurrentUserId();
        return ImageUrlResponse.from(
                s3PresignedUrlService.getPreSignedUrl(userId, PresignedType.USER));
    }
}
