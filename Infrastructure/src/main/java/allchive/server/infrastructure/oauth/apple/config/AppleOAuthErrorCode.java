package allchive.server.infrastructure.oauth.apple.config;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppleOAuthErrorCode {
    ;
    private Integer status;
    private String errorCode;
    private String error;
    private String reason;
}
