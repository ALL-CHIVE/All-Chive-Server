package allchive.server.domain.domains.search.exception;

import static allchive.server.core.consts.AllchiveConst.*;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchErrorCode implements BaseErrorCode {
    NO_AUTHORITY_UPDATE_LATEST_SEARCH(FORBIDDEN, "LATESTSEARCH_403_1", "최근 검색어 수정 권한이 없습니다."),
    LATEST_SEARCH_NOT_FOUND(NOT_FOUND, "LATESTSEARCH_404_1", "최근 검색어를 찾을 수 없습니다.");
    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
