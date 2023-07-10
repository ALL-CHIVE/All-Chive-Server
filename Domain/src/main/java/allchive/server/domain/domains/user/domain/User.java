package allchive.server.domain.domains.user.domain;


import allchive.server.domain.common.convertor.StringListConverter;
import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.archiving.domain.enums.Subject;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.UserRole;
import allchive.server.domain.domains.user.domain.enums.UserState;
import allchive.server.domain.domains.user.exception.exceptions.AlreadyDeletedUserException;
import allchive.server.domain.domains.user.exception.exceptions.ForbiddenUserException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
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

    private String name;

    @NotNull private String nickname;

    private String profileImgUrl;

    @Embedded private OauthInfo oauthInfo;

    @NotNull private LocalDateTime lastLoginAt;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserState userState = UserState.NORMAL;

    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @Convert(converter = StringListConverter.class)
    private List<Subject> subjects = new ArrayList<>();

    @Builder
    private User(String nickname, String profileImgUrl, OauthInfo oauthInfo) {
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
        this.oauthInfo = oauthInfo;
        this.lastLoginAt = LocalDateTime.now();
    }

    public static User of(String nickname, String profileImgUrl, OauthInfo oauthInfo) {
        return User.builder()
                .nickname(nickname)
                .profileImgUrl(profileImgUrl)
                .oauthInfo(oauthInfo)
                .build();
    }

    public void login() {
        if (!UserState.NORMAL.equals(this.userState)) {
            throw ForbiddenUserException.EXCEPTION;
        }
        lastLoginAt = LocalDateTime.now();
    }

    public void withdrawUser() {
        if (UserState.DELETED.equals(this.userState)) {
            throw AlreadyDeletedUserException.EXCEPTION;
        }
        this.userState = UserState.DELETED;
        this.nickname = LocalDateTime.now() + "삭제한 유저";
        this.profileImgUrl = null;
        this.email = null;
        this.oauthInfo.withDrawOauthInfo();
        this.subjects = new ArrayList<>();
        this.name = null;
    }

    public void updateInfo(String name, String email, String nickname, String imgUrl) {
        if (!UserState.NORMAL.equals(this.userState)) {
            throw ForbiddenUserException.EXCEPTION;
        }
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.profileImgUrl = imgUrl;
    }
}
