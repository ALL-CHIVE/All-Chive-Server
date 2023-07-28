package allchive.server.api.auth.model.dto.request;


import allchive.server.core.annotation.ValidEnum;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @Schema(
            defaultValue =
                    "https://asset.allchive.co.kr/staging/archiving/1/d241218a-a64c-4443-8aa4-ce98017a3d12",
            description = "프로필 이미지 url")
    @NotBlank(message = "프로필 이미지 url을 입력하세요")
    private String profileImgUrl;

    @Schema(defaultValue = "닉네임", description = "닉네임")
    @NotBlank(message = "닉네임을 입력하세요")
    private String nickname;

    @ArraySchema(schema = @Schema(description = "관심 주제", defaultValue = "FOOD"))
    private List<@ValidEnum(target = Category.class) Category> categories;

    @Schema(defaultValue = "이름", description = "이름, 애플 회원가입용")
    private String name;

    @Schema(defaultValue = "이메일", description = "이메일, 애플 회원가입용")
    private String email;
}
