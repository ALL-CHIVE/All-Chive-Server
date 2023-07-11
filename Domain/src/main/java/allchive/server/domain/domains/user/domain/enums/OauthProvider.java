package allchive.server.domain.domains.user.domain.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OauthProvider {
    KAKAO("KAKAO"),
    APPLE("APPLE");

    @JsonValue private String value;

    @JsonCreator
    public static OauthProvider parsing(String inputValue) {
        return Stream.of(OauthProvider.values())
                .filter(oauthProvider -> oauthProvider.getValue().equals(inputValue))
                .findFirst()
                .orElse(null);
    }
}
