package allchive.server.api.auth.model.dto.response;


import allchive.server.api.auth.model.vo.TokenVo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponse {
    @Schema(description = "로그인 가능 여부")
    private boolean canLogin;

    @JsonUnwrapped private TokenVo tokenVo;

    @Builder
    private SignInResponse(boolean canLogin, TokenVo tokenVo) {
        this.canLogin = canLogin;
        this.tokenVo = tokenVo;
    }

    public static SignInResponse of(
            boolean canLogin,
            String accessToken,
            Long accessTokenAge,
            String refreshToken,
            Long refreshTokenAge) {
        return SignInResponse.builder()
                .canLogin(canLogin)
                .tokenVo(TokenVo.of(accessToken, accessTokenAge, refreshToken, refreshTokenAge))
                .build();
    }

    public static SignInResponse cannotLogin() {
        return SignInResponse.builder()
                .canLogin(false)
                .tokenVo(TokenVo.of(null, null, null, null))
                .build();
    }
}
