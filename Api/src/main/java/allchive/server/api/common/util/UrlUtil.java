package allchive.server.api.common.util;

import static allchive.server.core.consts.AllchiveConst.*;

import allchive.server.core.helper.SpringEnvironmentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlUtil {
    private static SpringEnvironmentHelper springEnvironmentHelper;

    @Autowired
    private UrlUtil(SpringEnvironmentHelper springEnvironmentHelper) {
        UrlUtil.springEnvironmentHelper = springEnvironmentHelper;
    }

    public static String toAssetUrl(String key) {
        if (key.startsWith("http")) {
            return key;
        }
        if (key.equals("")) {
            return "";
        }
        if (springEnvironmentHelper.isProdProfile()) {
            return PROD_ASSET_URL + key;
        }
        return STAGING_ASSET_URL + key;
    }

    public static String convertUrlToKey(String url) {
        if (url == null) {
            return "";
        }
        if (url.equals("")) {
            return "";
        }
        if (validateS3Url(url)) {
            return url.split("/", 4)[3].split("\\?", 2)[0];
        }
        return url;
    }

    public static Boolean validateS3Url(String url) {
        return url.contains(STAGING_ASSET_URL)
                || url.contains(PROD_ASSET_URL)
                || url.contains(S3_STAGING_ASSET_URL)
                || url.contains(S3_PROD_ASSET_URL);
    }

    public static Boolean validateS3Key(String key) {
        return key != null && !key.startsWith("https") && !key.isEmpty();
    }
}
