package allchive.server.domain.domains.recycle.domain.enums;


import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecycleType {
    CONTENT("CONTENT"),
    ARCHIVING("ARCHIVING");

    @JsonValue private String value;

    @JsonCreator
    public static OauthProvider parsing(String inputValue) {
        return Stream.of(OauthProvider.values())
                .filter(type -> type.getValue().equals(inputValue))
                .findFirst()
                .orElse(null);
    }
}
