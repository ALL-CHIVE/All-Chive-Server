package allchive.server.infrastructure.oauth.apple.client;


import allchive.server.infrastructure.oauth.apple.config.AppleOAuthConfig;
import allchive.server.infrastructure.oauth.kakao.dto.OIDCPublicKeysResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "AppleOIDCClient",
        url = "https://appleid.apple.com",
        configuration = AppleOAuthConfig.class)
public interface AppleOIDCClient {
    @Cacheable(cacheNames = "appleOIDC", cacheManager = "oidcCacheManager")
    @GetMapping("/auth/keys")
    OIDCPublicKeysResponse getAppleOIDCOpenKeys();
}
