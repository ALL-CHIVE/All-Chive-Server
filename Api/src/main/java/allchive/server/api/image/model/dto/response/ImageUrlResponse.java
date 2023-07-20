package allchive.server.api.image.model.dto.response;


import allchive.server.infrastructure.s3.ImageUrlDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageUrlResponse {
    @Schema(defaultValue = "이미지 presigned url", description = "이미지 업로드에 사용")
    private String url;

    @Builder
    private ImageUrlResponse(String url) {
        this.url = url;
    }

    public static ImageUrlResponse from(ImageUrlDto imageUrlDto) {
        return ImageUrlResponse.builder().url(imageUrlDto.getUrl()).build();
    }
}
