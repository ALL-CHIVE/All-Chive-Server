package allchive.server.domain.domains.report.domain.enums;


import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportedType {
    SPAM("spam"), // 스팸이에요
    OBSCENE("obscene"), // 음란성 컨텐츠를 담고있어요
    PERSONAL_INFO("personalInfo"), // 개인정보를 노출하고 있어요
    INTELLECTUAL_PROPERTY("intellectualProperty"), // 지식재산권을 침해해요
    VIOLENCE("violence"), // 혐오/폭력 컨텐츠를 담고 있어요
    ILLEGAL_INFO("illegalInformation"), // 불법 정보를 포함하고 있어요
    FRAUD("fraud"), // 사기 또는 피싱성 링크를 포함하고 있어요
    ETC("etc"); // 기타

    @JsonValue private String value;

    @JsonCreator
    public static OauthProvider parsing(String inputValue) {
        return Stream.of(OauthProvider.values())
                .filter(type -> type.getValue().equals(inputValue))
                .findFirst()
                .orElse(null);
    }
}
