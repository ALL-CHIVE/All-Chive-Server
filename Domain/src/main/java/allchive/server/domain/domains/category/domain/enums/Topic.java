package allchive.server.domain.domains.category.domain.enums;


import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {
    ALL("ALL"),
    FOOD("FOOD"),
    LIFE("LIFE"),
    HOME_LIVING("HOME_LIVING"),
    SHOPPING("SHOPPING"),
    SPORT("SPORT"),
    SELF_IMPROVEMENT("SELF_IMPROVEMENT"),
    TECH("TECH"),
    DESIGN("DESIGN"),
    TREND("TREND");

    @JsonValue private String value;

    @JsonCreator
    public static OauthProvider parsing(String inputValue) {
        return Stream.of(OauthProvider.values())
                .filter(category -> category.getValue().equals(inputValue))
                .findFirst()
                .orElse(null);
    }
}
