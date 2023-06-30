package allchive.server.domain.domains.user.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.UserRole;
import allchive.server.domain.domains.user.domain.enums.UserState;
import allchive.server.domain.domains.user.exception.exceptions.ForbiddenUserException;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_user")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private String nickname;
    private String profileImgUrl;

    @Embedded private OauthInfo oauthInfo;

    @NotNull private LocalDateTime lastLoginAt;

    private String Email;

    @Enumerated(EnumType.STRING)
    private UserState userState = UserState.NORMAL;

    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    public void login() {
        if (!UserState.NORMAL.equals(this.userState)) {
            throw ForbiddenUserException.EXCEPTION;
        }
        lastLoginAt = LocalDateTime.now();
    }
}
