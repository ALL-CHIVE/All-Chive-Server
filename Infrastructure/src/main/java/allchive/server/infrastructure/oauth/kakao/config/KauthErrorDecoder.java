package allchive.server.infrastructure.oauth.kakao.config;


import allchive.server.infrastructure.oauth.kakao.dto.KakaoKauthErrorResponse;
import allchive.server.infrastructure.oauth.kakao.exception.KakaoKauthErrorCode;
import feign.Response;
import feign.codec.ErrorDecoder;

public class KauthErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        KakaoKauthErrorResponse body = KakaoKauthErrorResponse.from(response);

        try {
            KakaoKauthErrorCode kakaoKauthErrorCode =
                    KakaoKauthErrorCode.valueOf(body.getErrorCode());
            throw kakaoKauthErrorCode.getDynamicException();
        } catch (IllegalArgumentException e) {
            KakaoKauthErrorCode koeInvalidRequest = KakaoKauthErrorCode.KOE_INVALID_REQUEST;
            throw koeInvalidRequest.getDynamicException();
        }
    }
}
