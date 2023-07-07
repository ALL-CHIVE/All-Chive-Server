package allchive.server.api.image.model.dto.response;


import allchive.server.infrastructure.s3.ImageUrlDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageUrlResponse {
    @Schema(defaultValue = "이미지 presigned url", description = "이미지 업로드에 사용")
    private String url;

    @Schema(defaultValue = "이미지 고유 key 값", description = "서버로 이 값만 보내주시면 됩니다")
    private String key;

    @Builder
    private ImageUrlResponse(String url, String key) {
        this.url = url;
        this.key = key;
    }

    public static ImageUrlResponse from(ImageUrlDto imageUrlDto) {
        return ImageUrlResponse.builder()
                .key(imageUrlDto.getKey())
                .url(imageUrlDto.getUrl())
                .build();
    }
}
