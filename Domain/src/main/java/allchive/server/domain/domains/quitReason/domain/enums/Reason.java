package allchive.server.domain.domains.quitReason.domain.enums;

import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Reason {
    NOT_USED("NOT_USED"), // 자주 사용하지 않아서
    UNCONFORTABLE("UNCONFORTABLE"), // 이용이 불편하고 장애가 많아서
    USE_OTHER_SERVICE("USE_OTHER_SERVICE"), //다른 대체 서비스를 이용해서
    EXPENSIVE("EXPENSIVE"), // 서비스 이용료가 비싸서
    NEW_ACCOUNT("NEW_ACCOUNT"), //새 계정을 만들고싶어서
    ETC("ETC");

    @JsonValue
    private String value;

    @JsonCreator
    public static Reason parsing(String inputValue) {
        return Stream.of(Reason.values())
                .filter(type -> type.getValue().equals(inputValue))
                .findFirst()
                .orElse(null);
    }
}
