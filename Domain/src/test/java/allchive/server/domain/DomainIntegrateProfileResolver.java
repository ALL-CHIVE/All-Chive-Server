package allchive.server.domain;


import org.springframework.test.context.ActiveProfilesResolver;

public class DomainIntegrateProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> testClass) {
        // some code to find out your active profiles
        return new String[] {"core", "infrastructure", "domain"};
    }
}
