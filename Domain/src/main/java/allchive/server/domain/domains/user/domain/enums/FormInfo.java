package allchive.server.domain.domains.user.domain.enums;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FormInfo {
    private String formId;
    private String formPwd;
    private String phone;
}
