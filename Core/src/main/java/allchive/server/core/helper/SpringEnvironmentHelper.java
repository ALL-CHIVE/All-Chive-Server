package allchive.server.core.helper;

import static allchive.server.core.consts.AllchiveConst.DEV;
import static allchive.server.core.consts.AllchiveConst.PROD;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEnvironmentHelper {

    private final Environment environment;

    public Boolean isProdProfile() {
        List<String> currentProfile = getCurrentProfile();
        return currentProfile.contains(PROD);
    }

    public Boolean isDevProfile() {
        List<String> currentProfile = getCurrentProfile();
        return currentProfile.contains(DEV);
    }

    public Boolean isProdAndDevProfile() {
        List<String> currentProfile = getCurrentProfile();
        return currentProfile.contains(PROD) || currentProfile.contains(DEV);
    }

    private List<String> getCurrentProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        return Arrays.stream(activeProfiles).toList();
    }
}
