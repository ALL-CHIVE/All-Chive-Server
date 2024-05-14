package allchive.server.api.common.util;


import allchive.server.core.error.exception.EmptyParamValueException;
import org.springframework.stereotype.Component;

import static allchive.server.core.consts.AllchiveConst.HANGUL_BASE;
import static allchive.server.core.consts.AllchiveConst.KOREAN_ALPHA;

@Component
public class StringUtil {
    public static void checkEmptyString(String param) {
        if (param.equals("")) {
            throw EmptyParamValueException.EXCEPTION;
        }
    }

    public static char extractKoreanInitial(char ch) {
        int index = (ch - HANGUL_BASE) / (21 * 28); // 21개의 중성, 28개의 종성
        return KOREAN_ALPHA[index];
    }

    public static boolean isEnglish(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    public static boolean isKorean(char ch) {
        return (ch >= '\uAC00' && ch <= '\uD7A3');
    }
}
