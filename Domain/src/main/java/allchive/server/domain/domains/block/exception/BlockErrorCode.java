package allchive.server.domain.domains.block.exception;

import static allchive.server.core.consts.AllchiveConst.BAD_REQUEST;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlockErrorCode implements BaseErrorCode {
    DUPLICATED_BLOCK(BAD_REQUEST, "BLOCK_400_1", "이미 차단한 유저입니다."),
    CANNOT_BLOCK_MYSELF(BAD_REQUEST, "BLOCK_400_2", "본인을 차단할 수 없습니다."),
    ;
    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
