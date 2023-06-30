package allchive.server.infrastructure.oauth.kakao.client;


import allchive.server.infrastructure.oauth.kakao.config.KakaoInfoConfig;
import allchive.server.infrastructure.oauth.kakao.dto.KakaoInformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "KakaoInfoClient",
        url = "https://kapi.kakao.com",
        configuration = KakaoInfoConfig.class)
public interface KakaoInfoClient {

    @GetMapping("/v2/user/me")
    KakaoInformationResponse kakaoUserInfo(@RequestHeader("Authorization") String accessToken);
}
