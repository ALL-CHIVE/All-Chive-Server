package allchive.server.api.user.model.dto.response;

import allchive.server.api.common.util.UrlUtil;
import allchive.server.domain.domains.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserProfileResponse {
    @Schema(defaultValue = "닉네임", description = "닉네임")
    private String nickname;

    @Schema(defaultValue = "프로필 이미지 url", description = "프로필 이미지 url")
    private String imgUrl;

    @Schema(defaultValue = "0", description = "링크 개수")
    private int linkCount;

    @Schema(defaultValue = "0", description = "이미지 개수")
    private int imgCount;

    @Schema(defaultValue = "0", description = "공개 아카이브 개수")
    private int publicCategoryCount;

    @Builder
    private GetUserProfileResponse(String nickname, String imgUrl, int linkCount, int imgCount, int publicCategoryCount) {
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.linkCount = linkCount;
        this.imgCount = imgCount;
        this.publicCategoryCount = publicCategoryCount;
    }

    public static GetUserProfileResponse of(User user, int linkCount, int imgCount, int publicCategoryCount) {
        return GetUserProfileResponse.builder()
                .nickname(user.getNickname())
                .imgUrl(UrlUtil.toAssetUrl(user.getProfileImgUrl()))
                .linkCount(linkCount)
                .imgCount(imgCount)
                .publicCategoryCount(publicCategoryCount)
                .build();
    }
}
