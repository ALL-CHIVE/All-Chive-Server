package allchive.server.domain.domains.user.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserState {
    NORMAL("NORMAL"),
    // 탈퇴한유저
    DELETED("DELETED"),
    // 영구정지
    FORBIDDEN("FORBIDDEN"),
    // 일시정지
    SUSPENDED("SUSPENDED");

    private String value;
}
