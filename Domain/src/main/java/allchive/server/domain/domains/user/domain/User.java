package allchive.server.domain.domains.user.domain;

import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.UserState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Embedded
    private OauthInfo oauthInfo;

    @NotNull
    private LocalDateTime lastLoginAt;

    private String Email;

    @Enumerated(EnumType.STRING)
    private UserState userState = UserState.NORMAL;
}
