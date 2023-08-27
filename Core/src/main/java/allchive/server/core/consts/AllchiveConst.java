package allchive.server.core.consts;

public class AllchiveConst {
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String TOKEN_ROLE = "role";
    public static final String TOKEN_ISSUER = "ALLCHIVE";
    public static final String TOKEN_TYPE = "type";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";

    public static final String KID = "kid";
    public static final String KR_YES = "예";
    public static final String KR_NO = "아니요";

    public static final String PROD = "prod";
    public static final String DEV = "dev";

    public static final int MILLI_TO_SECOND = 1000;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER = 500;

    public static final String KAKAO_OAUTH_QUERY_STRING =
            "/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";

    public static final String APPLE_OAUTH_QUERY_STRING =
            "/auth/authorize?client_id=%s&redirect_uri=%s&response_type=code";

    public static final String STAGING_ASSET_URL = "https://asset.staging.allchive.co.kr/";
    public static final String PROD_ASSET_URL = "https://asset.allchive.co.kr/";
    public static final String S3_STAGING_ASSET_URL =
            "https://all-chive-dev-bucket.s3.ap-northeast-2.amazonaws.com/";
    public static final String S3_PROD_ASSET_URL =
            "https://all-chive-bucket.s3.ap-northeast-2.amazonaws.com/";

    public static final String[] SwaggerPatterns = {
        "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs",
    };

    public static final String SEARCH_KEY = "ARCHIVING_TITLE";
    public static final String ASTERISK = "*";

    public static final int PLUS_ONE = 1;
    public static final int MINUS_ONE = -1;
    public static final int ZERO = 0;

    public static final int CORE_POOL_SIZE = 1;
    public static final int MAX_POOL_SIZE = 20;
    public static final int QUEUE_CAPACITY = 500;
}
