package allchive.server.api.common.util;


import allchive.server.core.error.exception.EmptyParamValueException;
import org.springframework.stereotype.Component;

@Component
public class StringParamUtil {
    public static void checkEmptyString(String param) {
        if (param.equals("")) {
            throw EmptyParamValueException.EXCEPTION;
        }
    }
}
