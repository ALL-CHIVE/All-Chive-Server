package allchive.server.infrastructure.oauth.apple.client;


import allchive.server.infrastructure.oauth.apple.config.AppleOAuthConfig;
import allchive.server.infrastructure.oauth.apple.response.AppleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "AppleOAuthClient",
        url = "https://appleid.apple.com",
        configuration = AppleOAuthConfig.class)
public interface AppleOAuthClient {
    @PostMapping("/auth/token?grant_type=authorization_code")
    AppleTokenResponse appleAuth(
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("code") String code,
            @RequestParam("client_secret") String clientSecret);

    @PostMapping("/auth/revoke")
    void revoke(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("token") String accessToken);
}
