package allchive.server.infrastructure.s3;


import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageUrlDto {
    private String url;

    @Builder
    private ImageUrlDto(String url) {
        this.url = url;
    }

    public static ImageUrlDto of(String url) {
        return ImageUrlDto.builder().url(url).build();
    }
}
