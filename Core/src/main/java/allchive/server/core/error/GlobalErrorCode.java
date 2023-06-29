package allchive.server.core.error;

import static allchive.server.core.consts.AllchiveConst.*;

import allchive.server.core.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    /* example */
    EXAMPLE_ERROR(BAD_REQUEST, "GLOBAL_400_0", "에러 예시 입니다."),

    /* global error */
    HTTP_MESSAGE_NOT_READABLE(BAD_REQUEST, "GLOBAL_400_1", "잘못된 형식의 값을 입력했습니다."),
    _INTERNAL_SERVER_ERROR(INTERNAL_SERVER, "GLOBAL_500_1", "서버 오류. 관리자에게 문의 부탁드립니다."),

    /*토큰 에러*/
    // TODO : 에러 코드 정렬
    INVALID_TOKEN(UNAUTHORIZED, "AUTH_401_2", "올바르지 않은 토큰입니다."),
    INVALID_ACCESS_TOKEN_ERROR(UNAUTHORIZED, "AUTH_401_4", "알맞은 accessToken 을 넣어주세요."),
    EXPIRED_TOKEN(UNAUTHORIZED, "AUTH_401_3", "만료된 엑세스 토큰입니다"),
    EXPIRED_REFRESH_TOKEN(UNAUTHORIZED, "AUTH_401_5", "만료된 리프레시 토큰입니다"),
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "AUTH_401_2", "액세스 토큰이 유효하지 않습니다"),
    INVALID_REFRESH_TOKEN(UNAUTHORIZED, "AUTH_401_4", "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(UNAUTHORIZED, "AUTH_401_6", "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    FORBIDDEN_ADMIN(FORBIDDEN, "AUTH_403_1", "권한이 부여되지 않은 사용자입니다"),
    UNSUPPORTED_TOKEN(UNAUTHORIZED, "AUTH_401_7", "지원하지 않는 토큰입니다"),
    INVALID_SIGNATURE(UNAUTHORIZED, "AUTH_401_8", "잘못된 JWT 서명입니다"),
    NO_TOKEN(UNAUTHORIZED, "AUTH_401_1", "토큰이 존재하지 않습니다"),
    ;

    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
