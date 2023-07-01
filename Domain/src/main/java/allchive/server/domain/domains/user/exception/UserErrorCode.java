package allchive.server.domain.domains.user.exception;

import static allchive.server.core.consts.AllchiveConst.BAD_REQUEST;
import static allchive.server.core.consts.AllchiveConst.NOT_FOUND;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    FORBIDDEN_USER(BAD_REQUEST, "USER_400_1", "접근 제한된 유저입니다."),
    USER_ALREADY_SIGNUP(BAD_REQUEST, "USER_400_2", "이미 회원가입한 유저입니다."),
    USER_ALREADY_DELETED(BAD_REQUEST, "USER_400_3", "이미 지워진 유저입니다."),
    USER_NOT_FOUND(NOT_FOUND, "USER_404_1", "유저 정보를 찾을 수 없습니다.");
    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
