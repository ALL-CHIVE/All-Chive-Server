package allchive.server.domain.domains.recycle.domain.enums;

import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;
@Getter
@AllArgsConstructor
public enum RecycleType {
    CONTENT("content"),
    CATEGORY("category");

    @JsonValue
    private String value;

    @JsonCreator
    public static OauthProvider parsing(String inputValue) {
        return Stream.of(OauthProvider.values())
                .filter(category -> category.getValue().equals(inputValue))
                .findFirst()
                .orElse(null);
    }
}
