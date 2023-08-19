package allchive.server.api.common.json.naming;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class UpperCaseStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {
    @Override
    public String translate(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            char uc = Character.toUpperCase(c);
            sb.setCharAt(i, uc);
        }
        return sb.toString();
    }
}
