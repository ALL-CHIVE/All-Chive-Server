package allchive.server.api.common.util;

import static allchive.server.core.consts.AllchiveConst.PROD_ASSET_URL;
import static allchive.server.core.consts.AllchiveConst.STAGING_ASSET_URL;

import allchive.server.core.helper.SpringEnvironmentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlUtil {
    private static SpringEnvironmentHelper springEnvironmentHelper;

    @Autowired
    private UrlUtil(SpringEnvironmentHelper springEnvironmentHelper) {
        this.springEnvironmentHelper = springEnvironmentHelper;
    }

    public static String toAssetUrl(String key) {
        if (springEnvironmentHelper.isProdProfile()) {
            return PROD_ASSET_URL + key;
        }
        return STAGING_ASSET_URL + key;
    }
}