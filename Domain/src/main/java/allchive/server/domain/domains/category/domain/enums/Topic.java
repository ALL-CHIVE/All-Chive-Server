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
    FOOD("푸드"),
    LIFE("라이프"),
    HOME_LIVING("홈/리빙"),
    SHOPPING("쇼핑"),
    SPORT("스포츠"),
    SELF_IMPROVEMENT("자기 계발"),
    TECH("테크"),
    DESIGN("디자인"),
    TREND("트렌드");

    @JsonValue private String value;

    @JsonCreator
    public static OauthProvider parsing(String inputValue) {
        return Stream.of(OauthProvider.values())
                .filter(category -> category.getValue().equals(inputValue))
                .findFirst()
                .orElse(null);
    }
}
