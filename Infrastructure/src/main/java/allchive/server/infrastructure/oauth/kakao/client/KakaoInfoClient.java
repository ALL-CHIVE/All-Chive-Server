package allchive.server.infrastructure.oauth.kakao.client;


import allchive.server.infrastructure.oauth.kakao.config.KakaoInfoConfig;
import allchive.server.infrastructure.oauth.kakao.dto.KakaoInformationResponse;
import allchive.server.infrastructure.oauth.kakao.dto.KakaoUnlinkTarget;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "KakaoInfoClient",
        url = "https://kapi.kakao.com",
        configuration = KakaoInfoConfig.class)
public interface KakaoInfoClient {
    @PostMapping(path = "/v1/user/unlink", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void unlinkUser(
            @RequestHeader("Authorization") String adminKey, KakaoUnlinkTarget unlinkKaKaoTarget);

    @GetMapping("/v2/user/me")
    KakaoInformationResponse kakaoUserInfo(@RequestHeader("Authorization") String accessToken);
}
