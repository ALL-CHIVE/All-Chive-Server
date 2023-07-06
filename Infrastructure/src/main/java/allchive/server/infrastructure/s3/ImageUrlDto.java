package allchive.server.infrastructure.s3;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageUrlDto {
    private String url;
    private String key;

    @Builder
    private ImageUrlDto(String url, String key) {
        this.url = url;
        this.key = key;
    }

    public static ImageUrlDto of(String url, String key) {
        return ImageUrlDto.builder()
                .key(key)
                .url(url)
                .build();
    }
}
