package allchive.server.infrastructure.oauth.apple.config;


import allchive.server.core.error.BaseDynamicException;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.InputStream;
import lombok.SneakyThrows;

public class AppleOAuthErrorDecoder implements ErrorDecoder {
    @Override
    @SneakyThrows
    public Exception decode(String methodKey, Response response) {
        InputStream inputStream = response.body().asInputStream();
        byte[] byteArray = IOUtils.toByteArray(inputStream);
        String responseBody = new String(byteArray);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String error = jsonNode.get("error") == null ? null : jsonNode.get("error").asText();
        String errorDescription =
                jsonNode.get("error_description") == null
                        ? null
                        : jsonNode.get("error_description").asText();

        System.out.println(jsonNode);
        throw new BaseDynamicException(response.status(), error, errorDescription);
    }
}
